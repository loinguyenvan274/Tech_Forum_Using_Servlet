package com.diendan.Model.dao;

import com.diendan.Model.bean.NguoiDung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    
    public  NguoiDung themNguoiDung(NguoiDung nguoiDung) {
        String sql = "INSERT INTO nguoi_dung(tenDangNhap, tenHienThi, email, ngayThamGia, matKhau) values(?,?,?,?,?)";
        try(Connection conn = DBConnect.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, nguoiDung.getTenDangNhap());
                ps.setString(2, nguoiDung.getTenHienThi());
                ps.setString(3, nguoiDung.getEmail());
                ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                ps.setString(5, nguoiDung.getMatKhau());

                int affected = ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        nguoiDung.setMaNguoiDung(keys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nguoiDung;
    }
    
    public NguoiDung layNguoiDungTheoMa(int maNguoiDung) {
        String sql = "SELECT * from nguoi_dung WHERE maNguoiDung = ?";
        try(Connection conn = DBConnect.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1,String.valueOf(maNguoiDung));
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                       return new NguoiDung(rs.getInt("maNguoiDung"),rs.getString("tenDangNhap"),rs.getString("tenHienThi"),rs.getString("email"),rs.getDate("ngayThamGia"),rs.getString("matKhau"));

                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  null;
    }
    
    public List<NguoiDung> layTatCaNguoiDung() {
        List<NguoiDung> tatCaNguoiDung = new ArrayList<>();
        String sql = "SELECT * FROM nguoi_dung";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung(
                        rs.getInt("maNguoiDung"),
                        rs.getString("tenDangNhap"),
                        rs.getString("tenHienThi"),
                        rs.getString("email"),
                        rs.getDate("ngayThamGia"),
                        rs.getString("matKhau")
                );

                tatCaNguoiDung.add(nguoiDung);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tatCaNguoiDung;
    }

}
