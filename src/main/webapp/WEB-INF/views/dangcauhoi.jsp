<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Câu Hỏi - Diễn Đàn Công Nghệ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <!-- Header -->
        <header class="header">
            <div class="header-content">
                <h1 class="logo">Diễn Đàn Công Nghệ</h1>
                <nav class="nav">
                    <a href="${pageContext.request.contextPath}/" class="nav-link">Trang Chủ</a>
                    <a href="${pageContext.request.contextPath}/dangcauhoi" class="btn btn-primary">Đặt Câu Hỏi</a>
                </nav>
            </div>
        </header>
        
        <!-- Main Content -->
        <main class="main-content">
            <div class="form-container">
                <h2>Đăng Câu Hỏi Mới</h2>
                <p class="text-muted">Hãy mô tả câu hỏi của bạn một cách chi tiết để nhận được câu trả lời tốt nhất</p>
                
                <c:if test="${not empty loi}">
                    <div class="alert alert-error">
                        ${loi}
                    </div>
                </c:if>
                
                <form action="${pageContext.request.contextPath}/dangcauhoi" method="post" id="questionForm" class="form">
                    <div class="form-group">
                        <label for="tenNguoiHoi">Tên của bạn: <span class="required">*</span></label>
                        <input type="text" id="tenNguoiHoi" name="tenNguoiHoi" 
                               class="form-control" placeholder="Nhập tên của bạn" 
                               value="${tenNguoiHoi}" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="tieuDe">Tiêu đề câu hỏi: <span class="required">*</span></label>
                        <input type="text" id="tieuDe" name="tieuDe" 
                               class="form-control" placeholder="Ví dụ: Làm thế nào để kết nối Java với MySQL?" 
                               value="${tieuDe}" required>
                        <small class="form-help">Hãy viết tiêu đề ngắn gọn, rõ ràng và súc tích</small>
                    </div>
                    
                    <div class="form-group">
                        <label for="noiDung">Nội dung chi tiết: <span class="required">*</span></label>
                        <textarea id="noiDung" name="noiDung" rows="10" 
                                  class="form-control" placeholder="Mô tả chi tiết vấn đề của bạn..." required>${noiDung}</textarea>
                        <small class="form-help">Cung cấp đầy đủ thông tin về vấn đề bạn gặp phải</small>
                    </div>
                    
                    <div class="form-group">
                        <label for="tags">Tags (Nhãn):</label>
                        <input type="text" id="tags" name="tags" 
                               class="form-control" placeholder="Ví dụ: java, mysql, database" 
                               value="${tags}">
                        <small class="form-help">Phân tách các tag bằng dấu phẩy (,)</small>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Đăng Câu Hỏi</button>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Hủy</a>
                    </div>
                </form>
            </div>
        </main>
        
        <!-- Footer -->
        <footer class="footer">
            <p>&copy; 2024 Diễn Đàn Công Nghệ. Xây dựng với Java Servlet MVC.</p>
        </footer>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
