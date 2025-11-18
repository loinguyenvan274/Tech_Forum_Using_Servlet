package com.diendan.servlet;

import com.diendan.bo.CauHoiBO;
import com.diendan.bo.TraLoiBO;
import com.diendan.dao.CauHoiDAO;
import com.diendan.dao.TraLoiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet Controller để hiển thị chi tiết câu hỏi và danh sách trả lời
 */
@WebServlet("/chitiet")
public class ChiTietCauHoiServlet extends HttpServlet {
    private CauHoiDAO cauHoiDAO;
    private TraLoiDAO traLoiDAO;
    
    @Override
    public void init() throws ServletException {
        cauHoiDAO = CauHoiDAO.getInstance();
        traLoiDAO = TraLoiDAO.getInstance();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Thiết lập encoding UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // Lấy mã câu hỏi từ parameter
        String maCauHoiStr = request.getParameter("ma");
        
        if (maCauHoiStr == null || maCauHoiStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        
        try {
            int maCauHoi = Integer.parseInt(maCauHoiStr);
            
            // Lấy thông tin câu hỏi
            CauHoiBO cauHoi = cauHoiDAO.layCauHoiTheoMa(maCauHoi);
            
            if (cauHoi == null) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
            
            // Lấy danh sách trả lời
            List<TraLoiBO> danhSachTraLoi = traLoiDAO.layDanhSachTraLoiTheoCauHoi(maCauHoi);
            
            // Gửi dữ liệu sang View
            request.setAttribute("cauHoi", cauHoi);
            request.setAttribute("danhSachTraLoi", danhSachTraLoi);
            request.getRequestDispatcher("/WEB-INF/views/chitiet.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
