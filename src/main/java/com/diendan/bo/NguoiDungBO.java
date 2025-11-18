package com.diendan.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Business Object đại diện cho Người Dùng trong hệ thống
 */
public class NguoiDungBO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int maNguoiDung;
    private String tenDangNhap;
    private String tenHienThi;
    private String email;
    private Date ngayThamGia;
    
    /**
     * Constructor mặc định
     */
    public NguoiDungBO() {
        this.ngayThamGia = new Date();
    }
    
    /**
     * Constructor đầy đủ tham số
     */
    public NguoiDungBO(int maNguoiDung, String tenDangNhap, String tenHienThi, String email) {
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.tenHienThi = tenHienThi;
        this.email = email;
        this.ngayThamGia = new Date();
    }
    
    // Getters và Setters
    public int getMaNguoiDung() {
        return maNguoiDung;
    }
    
    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
    
    public String getTenDangNhap() {
        return tenDangNhap;
    }
    
    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    
    public String getTenHienThi() {
        return tenHienThi;
    }
    
    public void setTenHienThi(String tenHienThi) {
        this.tenHienThi = tenHienThi;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getNgayThamGia() {
        return ngayThamGia;
    }
    
    public void setNgayThamGia(Date ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }
}
