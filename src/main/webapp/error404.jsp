<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Không Tìm Thấy Trang</title>
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
                <h2>404 - Không Tìm Thấy Trang</h2>
                <p>Rất tiếc, trang bạn đang tìm kiếm không tồn tại.</p>
                <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Quay về Trang Chủ</a>
            </div>
        </main>
        
        <footer class="footer">
              <p>Bài tập công nghệ web [Hồ Nguyễn Thảo Nguyên, Lê Nguyễn Châu Anh, Nguyễn Văn Lợi]</p>
        </footer>
    </div>
</body>
</html>
