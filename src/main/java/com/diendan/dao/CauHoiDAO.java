package com.diendan.dao;

import com.diendan.bean.CauHoi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class DAO để quản lý dữ liệu Câu Hỏi (in-memory storage)
 */
public class CauHoiDAO {
    private static CauHoiDAO instance;
    private Map<Integer, CauHoi> danhSachCauHoi;
    private int maTuDong;
    
    /**
     * Constructor private để implement Singleton pattern
     */
    private CauHoiDAO() {
        danhSachCauHoi = new HashMap<>();
        maTuDong = 1;
        
        // Thêm một số câu hỏi mẫu
        themCauHoiMau();
    }
    
    /**
     * Lấy instance duy nhất của CauHoiDAO
     */
    public static synchronized CauHoiDAO getInstance() {
        if (instance == null) {
            instance = new CauHoiDAO();
        }
        return instance;
    }
    
    /**
     * Thêm dữ liệu câu hỏi mẫu
     */
    private void themCauHoiMau() {
        CauHoi cauHoi1 = new CauHoi(0,
            "Làm thế nào để kết nối Java với MySQL?",
            "Tôi đang học lập trình Java và muốn kết nối với cơ sở dữ liệu MySQL. Xin hỏi các bạn cần những bước nào và thư viện gì để thực hiện điều này? Tôi đã thử tìm hiểu nhưng vẫn còn khá mơ hồ.",
            1, "Nguyễn Văn A");
        cauHoi1.setDanhSachTag(Arrays.asList("java", "mysql", "database"));
        themCauHoi(cauHoi1);
        
        CauHoi cauHoi2 = new CauHoi(0,
            "Sự khác biệt giữa abstract class và interface trong Java?",
            "Mình đang tìm hiểu về lập trình hướng đối tượng trong Java. Có thể giải thích rõ sự khác biệt giữa abstract class và interface không? Khi nào thì nên dùng cái nào?",
            2, "Trần Thị B");
        cauHoi2.setDanhSachTag(Arrays.asList("java", "oop", "interface"));
        themCauHoi(cauHoi2);
        
        CauHoi cauHoi3 = new CauHoi(0,
            "Làm sao để deploy ứng dụng Java lên server?",
            "Em có một ứng dụng web Java sử dụng Servlet và JSP. Giờ em muốn đưa lên server để mọi người có thể truy cập. Các anh chị có thể hướng dẫn các bước deploy không ạ?",
            1, "Nguyễn Văn A");
        cauHoi3.setDanhSachTag(Arrays.asList("java", "deployment", "tomcat"));
        themCauHoi(cauHoi3);
    }
    
    /**
     * Thêm câu hỏi mới
     */
    public synchronized CauHoi themCauHoi(CauHoi cauHoi) {
        cauHoi.setMaCauHoi(maTuDong++);
        danhSachCauHoi.put(cauHoi.getMaCauHoi(), cauHoi);
        return cauHoi;
    }
    
    /**
     * Lấy câu hỏi theo mã
     */
    public CauHoi layCauHoiTheoMa(int maCauHoi) {
        return danhSachCauHoi.get(maCauHoi);
    }
    
    /**
     * Lấy tất cả câu hỏi (sắp xếp theo ngày đăng mới nhất)
     */
    public List<CauHoi> layTatCaCauHoi() {
        return danhSachCauHoi.values().stream()
            .sorted((c1, c2) -> c2.getNgayDang().compareTo(c1.getNgayDang()))
            .collect(Collectors.toList());
    }
    
    /**
     * Tìm kiếm câu hỏi theo từ khóa trong tiêu đề hoặc nội dung
     */
    public List<CauHoi> timKiemCauHoi(String tuKhoa) {
        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            return layTatCaCauHoi();
        }
        
        String tuKhoaLowerCase = tuKhoa.toLowerCase();

        return danhSachCauHoi.values().stream()
            .filter(ch -> ch.getTieuDe().toLowerCase().contains(tuKhoaLowerCase) ||
                         ch.getNoiDung().toLowerCase().contains(tuKhoaLowerCase))
            .sorted((c1, c2) -> c2.getNgayDang().compareTo(c1.getNgayDang()))
            .collect(Collectors.toList());
    }
    
    /**
     * Cập nhật số lượng trả lời của câu hỏi
     */
    public synchronized void capNhatSoLuongTraLoi(int maCauHoi, int soLuong) {
        CauHoi cauHoi = danhSachCauHoi.get(maCauHoi);
        if (cauHoi != null) {
            cauHoi.setSoLuongTraLoi(soLuong);
        }
    }
}
