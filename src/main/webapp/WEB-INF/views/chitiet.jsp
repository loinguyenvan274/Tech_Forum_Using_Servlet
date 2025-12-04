<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${cauHoi.tieuDe} - Diễn Đàn Công Nghệ</title>
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
            <!-- Question Details -->
            <div class="question-detail">
                <h2 class="detail-title">${cauHoi.tieuDe}</h2>
                
                <div class="question-meta">
                    <span class="author">Hỏi bởi: <strong>${cauHoi.tenNguoiHoi}</strong></span>
                    <span class="date">
                        <fmt:formatDate value="${cauHoi.ngayDang}" pattern="dd/MM/yyyy HH:mm"/>
                    </span>
                </div>
                
                <div class="question-body">
                    <p>${cauHoi.noiDung}</p>
                </div>
                
                <div class="question-tags">
                    <c:forEach items="${cauHoi.danhSachTag}" var="tag">
                        <span class="tag">${tag}</span>
                    </c:forEach>
                </div>
            </div>
            
            <!-- Answers Section -->
            <div class="answers-section">
                <h3 class="section-title">
                    ${danhSachTraLoi.size()} Câu Trả Lời
                </h3>
                
                <div class="answer-list">
                    <c:forEach items="${danhSachTraLoi}" var="traLoi">
                        <div class="answer-item">
                            <div class="answer-body">
                                <p>${traLoi.noiDung}</p>
                            </div>
                            
                            <div class="answer-meta">
                                <span class="author">Trả lời bởi: <strong>${traLoi.tenNguoiTraLoi}</strong></span>
                                <span class="date">
                                    <fmt:formatDate value="${traLoi.ngayTraLoi}" pattern="dd/MM/yyyy HH:mm"/>
                                </span>
                            </div>
                        </div>
                    </c:forEach>
                    
                    <c:if test="${empty danhSachTraLoi}">
                        <p class="text-muted">Chưa có câu trả lời nào. Hãy là người đầu tiên trả lời!</p>
                    </c:if>
                </div>
            </div>
            
            <!-- Answer Form -->
            <div class="answer-form-section">
                <h3 class="section-title">Câu Trả Lời Của Bạn</h3>
                
                <c:if test="${param.loi == '1'}">
                    <div class="alert alert-error">
                        Vui lòng điền đầy đủ thông tin!
                    </div>
                </c:if>
                
                <form action="${pageContext.request.contextPath}/traloi" method="post" id="answerForm" class="form">
                    <input type="hidden" name="maCauHoi" value="${cauHoi.maCauHoi}">
                    
                    <div class="form-group">
                        <label for="tenNguoiTraLoi">Tên của bạn: <span class="required">*</span></label>
                        <input type="text" id="tenNguoiTraLoi" name="tenNguoiTraLoi" 
                               class="form-control" placeholder="Nhập tên của bạn" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="noiDung">Nội dung trả lời: <span class="required">*</span></label>
                        <textarea id="noiDung" name="noiDung" rows="8" 
                                  class="form-control" placeholder="Nhập câu trả lời của bạn..." required></textarea>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Gửi Câu Trả Lời</button>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Hủy</a>
                    </div>
                </form>
            </div>
        </main>
        
        <!-- Footer -->
        <footer class="footer">
              <p>Bài tập công nghệ web [Hồ Nguyễn Thảo Nguyên, Lê Nguyễn Châu Anh, Nguyễn Văn Lợi]</p>
        </footer>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
