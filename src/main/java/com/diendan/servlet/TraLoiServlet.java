package com.diendan.servlet;

import com.diendan.Model.BO.TraLoiBO;
import com.diendan.Model.bean.TraLoi;
import com.diendan.Model.dao.TraLoiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet Controller để xử lý trả lời câu hỏi
 */
@WebServlet("/traloi")
public class TraLoiServlet extends HttpServlet {
    private TraLoiBO traLoiBO;
    
    @Override
    public void init() throws ServletException {
        traLoiBO = new TraLoiBO();
    }
    /**
     * Xử lý submit form trả lời
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Lấy dữ liệu từ form
        String maCauHoiStr = request.getParameter("maCauHoi");
        String noiDung = request.getParameter("noiDung");
        String tenNguoiTraLoi = request.getParameter("tenNguoiTraLoi");
        
        // Kiểm tra validation
        if (maCauHoiStr == null || maCauHoiStr.isEmpty() ||
            noiDung == null || noiDung.trim().isEmpty() ||
            tenNguoiTraLoi == null || tenNguoiTraLoi.trim().isEmpty()) {
            
            // Quay lại trang chi tiết với thông báo lỗi
            if (maCauHoiStr != null && !maCauHoiStr.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/chitiet?ma=" + maCauHoiStr + "&loi=1");
            } else {
                response.sendRedirect(request.getContextPath() + "/");
            }
            return;
        }
        
        try {
            int maCauHoi = Integer.parseInt(maCauHoiStr);
            
            // Tạo trả lời mới
            TraLoi traLoi = new TraLoi();
            traLoi.setMaCauHoi(maCauHoi);
            traLoi.setNoiDung(noiDung.trim());
            traLoi.setTenNguoiTraLoi(tenNguoiTraLoi.trim());
            traLoi.setMaNguoiTraLoi(1); // Mặc định người dùng ID 1
            
            // Lưu vào database
            traLoiBO.themTraLoi(traLoi);
            
            // Chuyển hướng về trang chi tiết câu hỏi
            response.sendRedirect(request.getContextPath() + "/chitiet?ma=" + maCauHoi);
            
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
