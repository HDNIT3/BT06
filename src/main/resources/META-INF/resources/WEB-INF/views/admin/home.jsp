<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home</title>
<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f0f2f5;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.home-container {
	max-width: 900px;
	margin: 40px auto;
	padding: 30px;
	background: #ffffff;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

h1 {
	font-weight: 700;
	color: #333;
	margin-bottom: 25px;
}

.search-box {
	margin-bottom: 25px;
}

.list-group-item {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-radius: 8px !important;
	padding: 15px;
}

.category-name {
	font-size: 18px;
	font-weight: 600;
	color: #333;
}

.category-buttons form {
	display: inline-block;
	margin: 0 3px;
}
</style>
</head>
<body>
	<div class="home-container">
		<h1 class="text-center">Trang Quản Trị</h1>

		<div class="search-box">
			<form action="/admin/search" method="post" class="d-flex">
				<input type="text" name="name" class="form-control me-2"
					placeholder="Nhập tên category..." required>
				<button type="submit" class="btn btn-primary">Tìm kiếm</button>
			</form>
		</div>

		<!-- Danh sách category dạng list -->
		<ul class="list-group">
			<c:forEach var="cate" items="${listcate}">
				<li class="list-group-item">
					<div class="category-name">${cate.categoryName}</div>
					<div class="category-buttons">
						<form action="/admin/video/home" method="get">
							<input type="hidden" name="id" value="${cate.id}" />
							<button type="submit" class="btn btn-outline-primary btn-sm">Xem Video</button>
						</form>

						<form action="/admin/category/delete" method="get">
							<input type="hidden" name="id" value="${cate.id}" />
							<button type="submit" class="btn btn-outline-danger btn-sm">Xóa</button>
						</form>
					</div>
				</li>
			</c:forEach>
		</ul>

		<!-- Nút chức năng -->
		<div class="text-center mt-4">
			<a href="/admin/logout" class="btn btn-secondary me-2">Đăng xuất</a>
			<a href="/admin/category/add" class="btn btn-primary">Thêm Category</a>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
