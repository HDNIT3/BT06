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

.category-grid {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
	/* responsive, max 5 per row */
	gap: 20px;
	margin-top: 30px;
}

.category-card {
	background: linear-gradient(135deg, #6c63ff, #5a55da);
	color: white;
	border-radius: 12px;
	padding: 20px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	transition: transform 0.3s, box-shadow 0.3s;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.category-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}

.category-name {
	font-size: 18px;
	font-weight: 600;
	margin-bottom: 12px;
	text-align: center;
}

.category-buttons {
	display: flex;
	gap: 10px;
}

.category-buttons form {
	margin: 0;
}

.category-buttons button {
	font-size: 0.8rem;
	padding: 5px 10px;
}

.logout-btn, .add-category-btn {
	margin-top: 20px;
}
</style>
</head>
<body>
	<div class="home-container">
		<h1 class="text-center">Trang Quản Trị</h1>

		<div class="category-grid">
			<c:forEach var="cate" items="${listcate}">
				<div class="category-card">
					<div class="category-name">${cate.categoryName}</div>

					<div class="category-buttons">
						<form action="/admin/video/home" method="get">
							<input type="hidden" name="id" value="${cate.id}" />
							<button type="submit" class="btn btn-light btn-sm">Xem
								Video</button>
						</form>

						<form action="/admin/category/delete" method="get">
							<input type="hidden" name="id" value="${cate.id}" />
							<button type="submit" class="btn btn-danger btn-sm">Xóa</button>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>

		<div class="text-center logout-btn">
			<a href="/admin/logout" class="btn btn-secondary">Đăng xuất</a>
		</div>
		<div class="text-center add-category-btn">
			<a href="/admin/category/add" class="btn btn-primary">Thêm
				Category</a>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
