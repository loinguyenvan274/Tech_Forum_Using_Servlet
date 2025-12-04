package com.diendan.Model.dao;

import com.diendan.Model.bean.CauHoi;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CauHoiDAO {
    // ===============================
    // 1. Thêm câu hỏi (kèm tag, cập nhật danhSachTagText)
    // ===============================
    public CauHoi themCauHoi(CauHoi cauHoi) {
        String sql = "INSERT INTO cau_hoi (tieuDe, noiDung, maNguoiHoi, tenNguoiHoi, ngayDang, soLuongTraLoi, danhSachTagText) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(false); // transaction: insert cau_hoi + tags + relations + update tagText

            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cauHoi.getTieuDe());
                ps.setString(2, cauHoi.getNoiDung());
                ps.setInt(3, cauHoi.getMaNguoiHoi());
                ps.setString(4, cauHoi.getTenNguoiHoi());
                ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                ps.setInt(6, 0);

                // nếu đã có danhSachTagText từ object thì dùng, còn không thì tạo từ
                // danhSachTag
                String tagText = buildTagText(cauHoi.getDanhSachTag());
                ps.setString(7, tagText);

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int maCauHoi = rs.getInt(1);
                        cauHoi.setMaCauHoi(maCauHoi);

                        // Lưu N-N (tag + cau_hoi_tag) - tái sử dụng conn
                        luuTagCuaCauHoi(maCauHoi, cauHoi.getDanhSachTag(), conn);

                        // Nếu muốn đảm bảo consistency, cập nhật lại danhSachTagText từ tags đã lưu
                        String finalTagText = buildTagText(cauHoi.getDanhSachTag());
                        capNhatDanhSachTagText(maCauHoi, finalTagText, conn);
                    }
                }
            }

            conn.commit();
            return cauHoi;

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    /* ignore */ }
            }
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    // ===============================
    // 2. Lưu tag và quan hệ N-N (sử dụng cùng Connection)
    // ===============================
    private void luuTagCuaCauHoi(int maCauHoi, List<String> tags, Connection conn) throws SQLException {
        if (tags == null || tags.isEmpty())
            return;

        // chuẩn hoá tags: trim, lowercase (tuỳ nhu cầu)
        List<String> normalized = tags.stream()
                .filter(t -> t != null && !t.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());

        String insertRelationSql = "INSERT IGNORE INTO cau_hoi_tag(maCauHoi, maTag) VALUES (?, ?)";
        for (String tag : normalized) {
            int maTag = timOrThemTag(tag, conn);

            try (PreparedStatement ps = conn.prepareStatement(insertRelationSql)) {
                ps.setInt(1, maCauHoi);
                ps.setInt(2, maTag);
                ps.executeUpdate();
            }
        }
    }

    // ===============================
    // 3. Tìm hoặc thêm tag (dùng connection hiện có)
    // ===============================
    private int timOrThemTag(String tag, Connection conn) throws SQLException {
        if (tag == null || tag.trim().isEmpty())
            return -1;
        String normalized = tag.trim();

        // 1. kiểm tra tồn tại
        String sqlCheck = "SELECT maTag FROM tag WHERE tenTag = ?";
        try (PreparedStatement ps = conn.prepareStatement(sqlCheck)) {
            ps.setString(1, normalized);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getInt("maTag");
            }
        }

        // 2. chưa có -> insert
        String sqlInsert = "INSERT INTO tag(tenTag) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, normalized);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        }

        // fallback
        return -1;
    }

    // ===============================
    // 4. Cập nhật cột denormalized danhSachTagText
    // ===============================
    private void capNhatDanhSachTagText(int maCauHoi, String tagText, Connection conn) throws SQLException {
        String sql = "UPDATE cau_hoi SET danhSachTagText = ? WHERE maCauHoi = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tagText);
            ps.setInt(2, maCauHoi);
            ps.executeUpdate();
        }
    }

    // ===============================
    // 5. Lấy tất cả câu hỏi (ưu tiên dùng danhSachTagText)
    // ===============================
    public List<CauHoi> layTatCaCauHoi() {
        List<CauHoi> ds = new ArrayList<>();
        String sql = "SELECT * FROM cau_hoi ORDER BY ngayDang DESC";

        try (Connection conn = DBConnect.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CauHoi c = new CauHoi(
                        rs.getInt("maCauHoi"),
                        rs.getString("tieuDe"),
                        rs.getString("noiDung"),
                        rs.getInt("maNguoiHoi"),
                        rs.getString("tenNguoiHoi"));

                c.setNgayDang(rs.getTimestamp("ngayDang"));
                c.setSoLuongTraLoi(rs.getInt("soLuongTraLoi"));

                String tagText = rs.getString("danhSachTagText");
                if (tagText != null && !tagText.trim().isEmpty()) {
                    c.setDanhSachTag(parseTagText(tagText));
                } else {
                    // fallback: nếu không có tagText thì query JOIN (ít xảy ra nếu bạn luôn cập
                    // nhật tagText)
                    c.setDanhSachTag(layTagTheoCauHoi(c.getMaCauHoi(), conn));
                }

                ds.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ds;
    }

    // ===============================
    // 6. Lấy tag của câu hỏi (dùng connection có sẵn)
    // ===============================
    private List<String> layTagTheoCauHoi(int maCauHoi, Connection conn) throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT t.tenTag FROM tag t "
                + "JOIN cau_hoi_tag cht ON t.maTag = cht.maTag "
                + "WHERE cht.maCauHoi = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCauHoi);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("tenTag"));
                }
            }
        }
        return list;
    }

    // Overload khi không có connection (rare)
    private List<String> layTagTheoCauHoi(int maCauHoi) {
        try (Connection conn = DBConnect.getConnection()) {
            return layTagTheoCauHoi(maCauHoi, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ===============================
    // 7. Helper: build tag text (CSV) từ list
    // ===============================
    private String buildTagText(List<String> tags) {
        if (tags == null || tags.isEmpty())
            return null;
        // nối bằng dấu phẩy, trim từng phần
        return tags.stream()
                .filter(t -> t != null && !t.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.joining(", "));
    }

    // ===============================
    // 8. Helper: parse tagText -> List<String>
    // ===============================
    private List<String> parseTagText(String tagText) {
        if (tagText == null || tagText.trim().isEmpty())
            return new ArrayList<>();
        return Arrays.stream(tagText.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    // ===============================
    // 9. Cập nhật số lượng trả lời (giữ nguyên)
    // ===============================
    public void capNhatSoLuongTraLoi(int maCauHoi, int soLuong) {
        String sql = "UPDATE cau_hoi SET soLuongTraLoi = ? WHERE maCauHoi = ?";

        try (Connection conn = DBConnect.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, soLuong);
            ps.setInt(2, maCauHoi);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ===============================
    // 10. Cập nhật tags cho câu hỏi: xóa quan hệ cũ, lưu quan hệ mới và cập nhật
    // danhSachTagText
    // ===============================
    public void capNhatTagsCuaCauHoi(int maCauHoi, List<String> newTags) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(false);

            // xóa quan hệ cũ
            try (PreparedStatement psDel = conn.prepareStatement("DELETE FROM cau_hoi_tag WHERE maCauHoi = ?")) {
                psDel.setInt(1, maCauHoi);
                psDel.executeUpdate();
            }

            // lưu quan hệ mới (đồng thời thêm tag nếu cần)
            luuTagCuaCauHoi(maCauHoi, newTags, conn);

            // cập nhật cột denormalized
            String newTagText = buildTagText(newTags);
            capNhatDanhSachTagText(maCauHoi, newTagText, conn);

            conn.commit();
        } catch (Exception e) {
            if (conn != null)
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
            throw new RuntimeException(e);
        } finally {
            if (conn != null)
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ignored) {
                }
        }
    }

    public CauHoi layCauHoiTheoMa(int maCauHoi) {
        String sql = "SELECT * FROM cau_hoi WHERE maCauHoi = ?";

        try (Connection conn = DBConnect.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maCauHoi);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCauHoi(rs, conn);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<CauHoi> timKiem(String tuKhoa, String tag) {
        List<CauHoi> ds = new ArrayList<>();
        String sql = "SELECT * FROM cau_hoi WHERE "
                + "tieuDe LIKE ? OR noiDung LIKE ? OR tenNguoiHoi LIKE ? "
                + "OR danhSachTagText LIKE ? "
                + "ORDER BY ngayDang DESC";

        try (Connection conn = DBConnect.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            String kw = "%" + tuKhoa + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);
            ps.setString(4, "%" + tag + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CauHoi c = mapCauHoi(rs, conn);
                    ds.add(c);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ds;
    }

    private CauHoi mapCauHoi(ResultSet rs, Connection conn) throws SQLException {
        CauHoi c = new CauHoi(
                rs.getInt("maCauHoi"),
                rs.getString("tieuDe"),
                rs.getString("noiDung"),
                rs.getInt("maNguoiHoi"),
                rs.getString("tenNguoiHoi"));

        c.setNgayDang(rs.getTimestamp("ngayDang"));
        c.setSoLuongTraLoi(rs.getInt("soLuongTraLoi"));

        String tagText = rs.getString("danhSachTagText");
        if (tagText != null && !tagText.trim().isEmpty()) {
            c.setDanhSachTag(parseTagText(tagText));
        } else {
            c.setDanhSachTag(layTagTheoCauHoi(c.getMaCauHoi(), conn));
        }

        return c;
    }

}
