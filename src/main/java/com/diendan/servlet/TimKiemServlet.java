package com.diendan.servlet;

import com.diendan.Model.BO.CauHoiBO;
import com.diendan.Model.bean.CauHoi;
import com.diendan.Model.dao.CauHoiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet Controller để tìm kiếm câu hỏi
 */
@WebServlet("/timkiem")
public class TimKiemServlet extends HttpServlet {
    private CauHoiBO cauHoiBO;
    
    @Override
    public void init() throws ServletException {
        cauHoiBO = new CauHoiBO()  ;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // Lấy từ khóa tìm kiếm
        String tuKhoa = request.getParameter("q");
        
        // Tìm kiếm câu hỏi
        List<CauHoi> danhSachCauHoi = cauHoiBO.timKiem(tuKhoa,tuKhoa);
        
        // Gửi dữ liệu sang View
        request.setAttribute("danhSachCauHoi", danhSachCauHoi);
        request.setAttribute("tuKhoa", tuKhoa);
        request.getRequestDispatcher("/WEB-INF/views/timkiem.jsp").forward(request, response);
    }
}
