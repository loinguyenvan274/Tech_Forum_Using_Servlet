package com.diendan.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Business Object đại diện cho Câu Trả Lời
 */
public class TraLoiBO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int maTraLoi;
    private int maCauHoi;
    private String noiDung;
    private int maNguoiTraLoi;
    private String tenNguoiTraLoi;
    private Date ngayTraLoi;
    
    /**
     * Constructor mặc định
     */
    public TraLoiBO() {
        this.ngayTraLoi = new Date();
    }
    
    /**
     * Constructor đầy đủ tham số
     */
    public TraLoiBO(int maTraLoi, int maCauHoi, String noiDung, int maNguoiTraLoi, String tenNguoiTraLoi) {
        this.maTraLoi = maTraLoi;
        this.maCauHoi = maCauHoi;
        this.noiDung = noiDung;
        this.maNguoiTraLoi = maNguoiTraLoi;
        this.tenNguoiTraLoi = tenNguoiTraLoi;
        this.ngayTraLoi = new Date();
    }
    
    // Getters và Setters
    public int getMaTraLoi() {
        return maTraLoi;
    }
    
    public void setMaTraLoi(int maTraLoi) {
        this.maTraLoi = maTraLoi;
    }
    
    public int getMaCauHoi() {
        return maCauHoi;
    }
    
    public void setMaCauHoi(int maCauHoi) {
        this.maCauHoi = maCauHoi;
    }
    
    public String getNoiDung() {
        return noiDung;
    }
    
    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
    
    public int getMaNguoiTraLoi() {
        return maNguoiTraLoi;
    }
    
    public void setMaNguoiTraLoi(int maNguoiTraLoi) {
        this.maNguoiTraLoi = maNguoiTraLoi;
    }
    
    public String getTenNguoiTraLoi() {
        return tenNguoiTraLoi;
    }
    
    public void setTenNguoiTraLoi(String tenNguoiTraLoi) {
        this.tenNguoiTraLoi = tenNguoiTraLoi;
    }
    
    public Date getNgayTraLoi() {
        return ngayTraLoi;
    }
    
    public void setNgayTraLoi(Date ngayTraLoi) {
        this.ngayTraLoi = ngayTraLoi;
    }
}
