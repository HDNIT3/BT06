<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Video</title>
    <!-- Bootstrap để form đẹp hơn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

    <h2 class="mb-4">Tải Video</h2>

    <form action="/admin/video/add" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${id}">
        <div class="mb-3">
            <label for="title" class="form-label">Tiêu đề Video</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>

        <!-- Chọn file video -->
        <div class="mb-3">
            <label for="file" class="form-label">Chọn Video File</label>
            <input type="file" class="form-control" id="file" name="file" accept="video/*" required>
        </div>

        <!-- Submit -->
        <button type="submit" class="btn btn-primary">Upload</button>
    </form>

</body>
</html>
