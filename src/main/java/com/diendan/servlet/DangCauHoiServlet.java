package com.diendan.servlet;

import com.diendan.bo.CauHoiBO;
import com.diendan.dao.CauHoiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet Controller để đăng câu hỏi mới
 */
@WebServlet("/dangcauhoi")
public class DangCauHoiServlet extends HttpServlet {
    private CauHoiDAO cauHoiDAO;
    
    @Override
    public void init() throws ServletException {
        cauHoiDAO = CauHoiDAO.getInstance();
    }
    
    /**
     * Hiển thị form đăng câu hỏi
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        request.getRequestDispatcher("/WEB-INF/views/dangcauhoi.jsp").forward(request, response);
    }
    
    /**
     * Xử lý submit form đăng câu hỏi
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Lấy dữ liệu từ form
        String tieuDe = request.getParameter("tieuDe");
        String noiDung = request.getParameter("noiDung");
        String tags = request.getParameter("tags");
        String tenNguoiHoi = request.getParameter("tenNguoiHoi");
        
        // Kiểm tra validation
        if (tieuDe == null || tieuDe.trim().isEmpty() ||
            noiDung == null || noiDung.trim().isEmpty() ||
            tenNguoiHoi == null || tenNguoiHoi.trim().isEmpty()) {
            
            request.setAttribute("loi", "Vui lòng điền đầy đủ thông tin!");
            request.setAttribute("tieuDe", tieuDe);
            request.setAttribute("noiDung", noiDung);
            request.setAttribute("tags", tags);
            request.setAttribute("tenNguoiHoi", tenNguoiHoi);
            request.getRequestDispatcher("/WEB-INF/views/dangcauhoi.jsp").forward(request, response);
            return;
        }
        
        // Tạo câu hỏi mới
        CauHoiBO cauHoi = new CauHoiBO();
        cauHoi.setTieuDe(tieuDe.trim());
        cauHoi.setNoiDung(noiDung.trim());
        cauHoi.setTenNguoiHoi(tenNguoiHoi.trim());
        cauHoi.setMaNguoiHoi(1); // Mặc định người dùng ID 1
        
        // Xử lý tags (phân tách bằng dấu phẩy)
        if (tags != null && !tags.trim().isEmpty()) {
            List<String> danhSachTag = Arrays.asList(tags.split(","));
            cauHoi.setDanhSachTag(danhSachTag);
        }
        
        // Lưu vào database
        cauHoiDAO.themCauHoi(cauHoi);
        
        // Chuyển hướng về trang chủ
        response.sendRedirect(request.getContextPath() + "/");
    }
}
