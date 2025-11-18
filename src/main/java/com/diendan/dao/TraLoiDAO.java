package com.diendan.dao;

import com.diendan.bo.TraLoiBO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class DAO để quản lý dữ liệu Câu Trả Lời (in-memory storage)
 */
public class TraLoiDAO {
    private static TraLoiDAO instance;
    private Map<Integer, TraLoiBO> danhSachTraLoi;
    private int maTuDong;
    
    /**
     * Constructor private để implement Singleton pattern
     */
    private TraLoiDAO() {
        danhSachTraLoi = new HashMap<>();
        maTuDong = 1;
        
        // Thêm một số trả lời mẫu
        themTraLoiMau();
    }
    
    /**
     * Lấy instance duy nhất của TraLoiDAO
     */
    public static synchronized TraLoiDAO getInstance() {
        if (instance == null) {
            instance = new TraLoiDAO();
        }
        return instance;
    }
    
    /**
     * Thêm dữ liệu trả lời mẫu
     */
    private void themTraLoiMau() {
        themTraLoi(new TraLoiBO(0, 1,
            "Để kết nối Java với MySQL, bạn cần làm các bước sau:\n\n" +
            "1. Thêm MySQL JDBC Driver vào project (mysql-connector-java)\n" +
            "2. Load driver: Class.forName(\"com.mysql.cj.jdbc.Driver\")\n" +
            "3. Tạo kết nối: Connection conn = DriverManager.getConnection(url, username, password)\n" +
            "4. Thực hiện query với Statement hoặc PreparedStatement\n\n" +
            "Chúc bạn thành công!",
            2, "Trần Thị B"));
        
        themTraLoi(new TraLoiBO(0, 1,
            "Bạn cũng nên sử dụng PreparedStatement thay vì Statement để tránh SQL Injection nhé!",
            1, "Nguyễn Văn A"));
        
        themTraLoi(new TraLoiBO(0, 2,
            "Abstract class và Interface khác nhau như sau:\n\n" +
            "Abstract Class:\n" +
            "- Có thể có cả phương thức abstract và concrete\n" +
            "- Có thể có biến instance\n" +
            "- Sử dụng extends, chỉ kế thừa được 1 class\n\n" +
            "Interface:\n" +
            "- Chỉ có phương thức abstract (trước Java 8)\n" +
            "- Chỉ có hằng số (static final)\n" +
            "- Sử dụng implements, có thể implement nhiều interface\n\n" +
            "Dùng abstract class khi các class con có quan hệ \"is-a\" chặt chẽ.\n" +
            "Dùng interface khi muốn định nghĩa hành vi mà nhiều class không liên quan có thể implement.",
            2, "Trần Thị B"));
    }
    
    /**
     * Thêm trả lời mới
     */
    public synchronized TraLoiBO themTraLoi(TraLoiBO traLoi) {
        traLoi.setMaTraLoi(maTuDong++);
        danhSachTraLoi.put(traLoi.getMaTraLoi(), traLoi);
        
        // Cập nhật số lượng trả lời cho câu hỏi
        int soLuong = layDanhSachTraLoiTheoCauHoi(traLoi.getMaCauHoi()).size();
        CauHoiDAO.getInstance().capNhatSoLuongTraLoi(traLoi.getMaCauHoi(), soLuong);
        
        return traLoi;
    }
    
    /**
     * Lấy danh sách trả lời theo mã câu hỏi
     */
    public List<TraLoiBO> layDanhSachTraLoiTheoCauHoi(int maCauHoi) {
        return danhSachTraLoi.values().stream()
            .filter(tl -> tl.getMaCauHoi() == maCauHoi)
            .sorted((t1, t2) -> t1.getNgayTraLoi().compareTo(t2.getNgayTraLoi()))
            .collect(Collectors.toList());
    }
    
    /**
     * Lấy trả lời theo mã
     */
    public TraLoiBO layTraLoiTheoMa(int maTraLoi) {
        return danhSachTraLoi.get(maTraLoi);
    }
}
