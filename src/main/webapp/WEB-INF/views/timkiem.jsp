<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tìm Kiếm - Diễn Đàn Công Nghệ</title>
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
            
            <!-- Search Form -->
            <div class="search-container">
                <form action="${pageContext.request.contextPath}/timkiem" method="get" class="search-form">
                    <input type="text" name="q" placeholder="Tìm kiếm câu hỏi..." 
                           class="search-input" value="${tuKhoa}">
                    <button type="submit" class="btn btn-search">Tìm Kiếm</button>
                </form>
            </div>
        </header>
        
        <!-- Main Content -->
        <main class="main-content">
            <div class="section-header">
                <h2>Kết Quả Tìm Kiếm</h2>
                <c:if test="${not empty tuKhoa}">
                    <p class="text-muted">Tìm thấy ${danhSachCauHoi.size()} kết quả cho từ khóa: "<strong>${tuKhoa}</strong>"</p>
                </c:if>
            </div>
            
            <div class="question-list">
                <c:forEach items="${danhSachCauHoi}" var="cauHoi">
                    <div class="question-item">
                        <div class="question-stats">
                            <div class="stat-item">
                                <span class="stat-number">${cauHoi.soLuongTraLoi}</span>
                                <span class="stat-label">trả lời</span>
                            </div>
                        </div>
                        
                        <div class="question-content">
                            <h3 class="question-title">
                                <a href="${pageContext.request.contextPath}/chitiet?ma=${cauHoi.maCauHoi}">
                                    ${cauHoi.tieuDe}
                                </a>
                            </h3>
                            <p class="question-excerpt">${cauHoi.tomTatNoiDung}</p>
                            
                            <div class="question-tags">
                                <c:forEach items="${cauHoi.danhSachTag}" var="tag">
                                    <span class="tag">${tag}</span>
                                </c:forEach>
                            </div>
                            
                            <div class="question-meta">
                                <span class="author">Hỏi bởi: <strong>${cauHoi.tenNguoiHoi}</strong></span>
                                <span class="date">
                                    <fmt:formatDate value="${cauHoi.ngayDang}" pattern="dd/MM/yyyy HH:mm"/>
                                </span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                
                <c:if test="${empty danhSachCauHoi}">
                    <div class="empty-state">
                        <p>Không tìm thấy kết quả phù hợp. Thử tìm kiếm với từ khóa khác!</p>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Quay lại trang chủ</a>
                    </div>
                </c:if>
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
