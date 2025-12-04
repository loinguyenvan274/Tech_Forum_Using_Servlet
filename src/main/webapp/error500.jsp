<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - Lỗi Máy Chủ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <div class="header-content">
                <h1 class="logo">Diễn Đàn Công Nghệ</h1>
            </div>
        </header>
        
        <main class="main-content">
            <div class="empty-state">
                <h2>500 - Lỗi Máy Chủ</h2>
                <p>Đã xảy ra lỗi trong quá trình xử lý yêu cầu của bạn.</p>
                <p>Vui lòng thử lại sau hoặc liên hệ quản trị viên.</p>
                <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Quay về Trang Chủ</a>
            </div>
        </main>
        
        <footer class="footer">
              <p>Bài tập công nghệ web [Hồ Nguyễn Thảo Nguyên, Lê Nguyễn Châu Anh, Nguyễn Văn Lợi]</p>
        </footer>
    </div>
</body>
</html>
