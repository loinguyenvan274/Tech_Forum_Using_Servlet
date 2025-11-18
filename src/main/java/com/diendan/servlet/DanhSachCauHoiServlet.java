package com.diendan.servlet;

import com.diendan.bo.CauHoiBO;
import com.diendan.dao.CauHoiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet Controller để hiển thị danh sách câu hỏi
 */
@WebServlet("/")
public class DanhSachCauHoiServlet extends HttpServlet {
    private CauHoiDAO cauHoiDAO;
    
    @Override
    public void init() throws ServletException {
        cauHoiDAO = CauHoiDAO.getInstance();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Thiết lập encoding UTF-8 để hiển thị tiếng Việt
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // Lấy danh sách tất cả câu hỏi
        List<CauHoiBO> danhSachCauHoi = cauHoiDAO.layTatCaCauHoi();
        
        // Gửi dữ liệu sang View (JSP)
        request.setAttribute("danhSachCauHoi", danhSachCauHoi);
        request.getRequestDispatcher("/WEB-INF/views/danhsach.jsp").forward(request, response);
    }
}
