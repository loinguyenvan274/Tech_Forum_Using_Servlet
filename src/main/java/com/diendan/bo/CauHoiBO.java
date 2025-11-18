package com.diendan.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Business Object đại diện cho Câu Hỏi trong diễn đàn
 */
public class CauHoiBO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int maCauHoi;
    private String tieuDe;
    private String noiDung;
    private int maNguoiHoi;
    private String tenNguoiHoi;
    private Date ngayDang;
    private List<String> danhSachTag;
    private int soLuongTraLoi;
    
    /**
     * Constructor mặc định
     */
    public CauHoiBO() {
        this.ngayDang = new Date();
        this.danhSachTag = new ArrayList<>();
        this.soLuongTraLoi = 0;
    }
    
    /**
     * Constructor đầy đủ tham số
     */
    public CauHoiBO(int maCauHoi, String tieuDe, String noiDung, int maNguoiHoi, String tenNguoiHoi) {
        this.maCauHoi = maCauHoi;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.maNguoiHoi = maNguoiHoi;
        this.tenNguoiHoi = tenNguoiHoi;
        this.ngayDang = new Date();
        this.danhSachTag = new ArrayList<>();
        this.soLuongTraLoi = 0;
    }
    
    // Getters và Setters
    public int getMaCauHoi() {
        return maCauHoi;
    }
    
    public void setMaCauHoi(int maCauHoi) {
        this.maCauHoi = maCauHoi;
    }
    
    public String getTieuDe() {
        return tieuDe;
    }
    
    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }
    
    public String getNoiDung() {
        return noiDung;
    }
    
    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
    
    public int getMaNguoiHoi() {
        return maNguoiHoi;
    }
    
    public void setMaNguoiHoi(int maNguoiHoi) {
        this.maNguoiHoi = maNguoiHoi;
    }
    
    public String getTenNguoiHoi() {
        return tenNguoiHoi;
    }
    
    public void setTenNguoiHoi(String tenNguoiHoi) {
        this.tenNguoiHoi = tenNguoiHoi;
    }
    
    public Date getNgayDang() {
        return ngayDang;
    }
    
    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }
    
    public List<String> getDanhSachTag() {
        return danhSachTag;
    }
    
    public void setDanhSachTag(List<String> danhSachTag) {
        this.danhSachTag = danhSachTag;
    }
    
    public int getSoLuongTraLoi() {
        return soLuongTraLoi;
    }
    
    public void setSoLuongTraLoi(int soLuongTraLoi) {
        this.soLuongTraLoi = soLuongTraLoi;
    }
    
    /**
     * Lấy tóm tắt nội dung câu hỏi (200 ký tự đầu)
     */
    public String getTomTatNoiDung() {
        if (noiDung == null) return "";
        if (noiDung.length() <= 200) return noiDung;
        return noiDung.substring(0, 200) + "...";
    }
}
