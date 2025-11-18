package com.diendan.dao;

import com.diendan.bo.NguoiDungBO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class DAO để quản lý dữ liệu Người Dùng (in-memory storage)
 */
public class NguoiDungDAO {
    private static NguoiDungDAO instance;
    private Map<Integer, NguoiDungBO> danhSachNguoiDung;
    private int maTuDong;
    
    /**
     * Constructor private để implement Singleton pattern
     */
    private NguoiDungDAO() {
        danhSachNguoiDung = new HashMap<>();
        maTuDong = 1;
        
        // Thêm một số dữ liệu mẫu
        themNguoiDungMau();
    }
    
    /**
     * Lấy instance duy nhất của NguoiDungDAO
     */
    public static synchronized NguoiDungDAO getInstance() {
        if (instance == null) {
            instance = new NguoiDungDAO();
        }
        return instance;
    }
    
    /**
     * Thêm dữ liệu người dùng mẫu
     */
    private void themNguoiDungMau() {
        themNguoiDung(new NguoiDungBO(0, "admin", "Quản Trị Viên", "admin@diendan.com"));
        themNguoiDung(new NguoiDungBO(0, "nguoidung1", "Nguyễn Văn A", "nguyenvana@email.com"));
        themNguoiDung(new NguoiDungBO(0, "nguoidung2", "Trần Thị B", "tranthib@email.com"));
    }
    
    /**
     * Thêm người dùng mới
     */
    public synchronized NguoiDungBO themNguoiDung(NguoiDungBO nguoiDung) {
        nguoiDung.setMaNguoiDung(maTuDong++);
        danhSachNguoiDung.put(nguoiDung.getMaNguoiDung(), nguoiDung);
        return nguoiDung;
    }
    
    /**
     * Lấy người dùng theo mã
     */
    public NguoiDungBO layNguoiDungTheoMa(int maNguoiDung) {
        return danhSachNguoiDung.get(maNguoiDung);
    }
    
    /**
     * Lấy tất cả người dùng
     */
    public List<NguoiDungBO> layTatCaNguoiDung() {
        return new ArrayList<>(danhSachNguoiDung.values());
    }
}
