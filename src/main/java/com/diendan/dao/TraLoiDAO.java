package com.diendan.dao;

import com.diendan.bean.TraLoi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class DAO để quản lý dữ liệu Câu Trả Lời (in-memory storage)
 */
public class TraLoiDAO {
    private static TraLoiDAO instance;
    private TraLoiDAO() {
    }
    
    public static synchronized TraLoiDAO getInstance() {
        if (instance == null) {
            instance = new TraLoiDAO();
        }
        return instance;
    }
    
  
    public synchronized TraLoi themTraLoi(TraLoi traLoi) {
        String sql = "INSERT INTO cau_tra_loi (maCauHoi, noiDung, maNguoiTraLoi, tenNguoiTraLoi, ngayTraLoi) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, traLoi.getMaCauHoi());
            ps.setString(2, traLoi.getNoiDung());
            ps.setInt(3, traLoi.getMaNguoiTraLoi());
            ps.setString(4, traLoi.getTenNguoiTraLoi());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    traLoi.setMaTraLoi(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    
        return traLoi;
    }
    
  
    public List<TraLoi> layDanhSachTraLoiTheoCauHoi(int maCauHoi) {
        String sql = "SELECT * FROM cau_tra_loi WHERE maCauHoi = ?";
        List<TraLoi> traLoiList = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCauHoi);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    traLoiList.add(mapTraLoi(rs, conn));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return traLoiList.stream().sorted(Comparator.comparing(TraLoi::getNgayTraLoi)).collect(Collectors.toList());
    }


    public TraLoi layTraLoiTheoMa(int maTraLoi) {
        String sql = "SELECT * FROM cau_tra_loi WHERE maTraLoi = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTraLoi);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapTraLoi(rs, conn);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    private TraLoi mapTraLoi(ResultSet rs, Connection conn) throws SQLException {
        TraLoi traLoi = new TraLoi(
            rs.getInt("maTraLoi"),
            rs.getInt("maCauHoi"),
            rs.getString("noiDung"),
            rs.getInt("maNguoiTraLoi"),
            rs.getString("tenNguoiTraLoi"));
        traLoi.setNgayTraLoi(rs.getTimestamp("ngayTraLoi"));
        return traLoi;
    }
}
