package com.diendan.servlet;

import com.diendan.Model.BO.CauHoiBO;
import com.diendan.Model.BO.TraLoiBO;
import com.diendan.Model.bean.CauHoi;
import com.diendan.Model.bean.TraLoi;
import com.diendan.Model.dao.CauHoiDAO;
import com.diendan.Model.dao.TraLoiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/chitiet")
public class ChiTietCauHoiServlet extends HttpServlet {
    private CauHoiBO cauHoiBO;
    private TraLoiBO traLoiBO;
    
    @Override
    public void init() throws ServletException {
        cauHoiBO = new CauHoiBO();
        traLoiBO = new TraLoiBO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Thiết lập encoding UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String maCauHoiStr = request.getParameter("ma");
        
        if (maCauHoiStr == null || maCauHoiStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        
        try {
            int maCauHoi = Integer.parseInt(maCauHoiStr);
            
            // Lấy thông tin câu hỏi
            CauHoi cauHoi = cauHoiBO.layCauHoiTheoMa(maCauHoi);
            
            if (cauHoi == null) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
            
            // Lấy danh sách trả lời
            List<TraLoi> danhSachTraLoi = traLoiBO.layDanhSachTraLoiTheoCauHoi(maCauHoi);
            
            // Gửi dữ liệu sang View
            request.setAttribute("cauHoi", cauHoi);
            request.setAttribute("danhSachTraLoi", danhSachTraLoi);
            request.getRequestDispatcher("/WEB-INF/views/chitiet.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
