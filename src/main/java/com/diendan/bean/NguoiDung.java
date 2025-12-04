package com.diendan.bean;

import java.io.Serializable;
import java.util.Date;

public class NguoiDung implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int maNguoiDung;
    private String tenDangNhap;
    private String tenHienThi;
    private String email;
    private Date ngayThamGia;
    private String matKhau;

    public NguoiDung() {
        this.ngayThamGia = new Date();
    }

    public NguoiDung(int maNguoiDung, String tenDangNhap, String tenHienThi, String email,String matKhau) {
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.tenHienThi = tenHienThi;
        this.email = email;
        this.ngayThamGia = new Date();
        this.matKhau = matKhau;

    }

    public NguoiDung(int maNguoiDung, String tenDangNhap, String tenHienThi, String email, Date ngayThamGia, String matKhau) {
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.tenHienThi = tenHienThi;
        this.email = email;
        this.ngayThamGia = ngayThamGia;
        this.matKhau = matKhau;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
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
