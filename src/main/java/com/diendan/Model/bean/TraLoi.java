package com.diendan.Model.bean;

import java.io.Serializable;
import java.util.Date;
 
public class TraLoi implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int maTraLoi;
    private int maCauHoi;
    private String noiDung;
    private int maNguoiTraLoi;
    private String tenNguoiTraLoi;
    private Date ngayTraLoi;
    

    public TraLoi() {
        this.ngayTraLoi = new Date();
    }
    
    public TraLoi(int maTraLoi, int maCauHoi, String noiDung, int maNguoiTraLoi, String tenNguoiTraLoi) {
        this.maTraLoi = maTraLoi;
        this.maCauHoi = maCauHoi;
        this.noiDung = noiDung;
        this.maNguoiTraLoi = maNguoiTraLoi;
        this.tenNguoiTraLoi = tenNguoiTraLoi;
        this.ngayTraLoi = new Date();
    }
    
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
