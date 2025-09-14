<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Video Management</title>
<!-- Bootstrap 5 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
    background-color: #f0f2f5;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.home-container {
    max-width: 1200px;
    margin: 40px auto;
    padding: 30px;
    background: #ffffff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

h1 {
    font-weight: 700;
    color: #333;
}

.video-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 20px;
    margin-top: 30px;
}

.video-card {
    background: linear-gradient(135deg, #00b09b, #96c93d);
    color: white;
    border-radius: 12px;
    padding: 20px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    transition: transform 0.3s, box-shadow 0.3s;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.video-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}

.video-title {
    font-size: 18px;
    font-weight: 600;
    margin-bottom: 12px;
    text-align: center;
}

.video-preview {
    width: 100%;
    border-radius: 8px;
    margin-bottom: 12px;
}

.video-buttons {
    display: flex;
    justify-content: center;
    gap: 10px;
}
</style>
</head>
<body>
    <div class="home-container">
        <h1 class="text-center">Quản lý Video</h1>

        <div class="video-grid">
            <c:forEach var="video" items="${listVideo}">
                <div class="video-card">
                    <div class="video-title">${video.title}</div>

                    <video class="video-preview" controls>
                        <source src="/admin/video/path/${video.filePath}" type="video/mp4">
                        Trình duyệt của bạn không hỗ trợ video.
                    </video>

                    <div class="video-buttons">
                        <form action="/admin/video/delete" method="get">
                            <input type="hidden" name="idvideo" value="${video.id}" />
                            <input type="hidden" name="id" value="${id}" />
                            <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="text-center mt-4">
            <a href="/admin/video/add?id=${id}" class="btn btn-primary">Thêm Video</a>
            <a href="/admin/home" class="btn btn-secondary">Quay lại</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
