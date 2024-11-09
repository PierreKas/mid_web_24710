<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Shelf List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
<div class="row mb-3">
<div class="col">
<h2><i class="bi bi-bookshelf"></i> Shelf List</h2>
</div>
<div class="col text-end">
<a href="${pageContext.request.contextPath}/shelf/new" class="btn btn-primary">
<i class="bi bi-plus-circle"></i> Add Shelf
</a>
</div>
</div>

<c:if test="${not empty sessionScope.successMessage}">
<div class="alert alert-success alert-dismissible fade show" role="alert">
${sessionScope.successMessage}
<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<c:remove var="successMessage" scope="session" />
</c:if>

<c:if test="${not empty sessionScope.errorMessage}">
<div class="alert alert-danger alert-dismissible fade show" role="alert">
${sessionScope.errorMessage}
<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<c:remove var="errorMessage" scope="session" />
</c:if>

<table class="table table-striped table-hover">
<thead>
<tr>
<th>ID</th>
<th>Book Category</th>
<th>Available Stock</th>
<th>Borrowed Number</th>
<th>Initial Stock</th>
<th>Room ID</th>
<th>Actions</th>
<th>Books</th>
</tr>
</thead>
<tbody>
<c:forEach var="shelf" items="${shelves}">
<tr>
<td>${shelf.id}</td>
<td>${shelf.book_category}</td>
<td>${shelf.available_stock}</td>
<td>${shelf.borrowed_number}</td>
<td>${shelf.initial_stock}</td>
<td>${shelf.room_id}</td>
<td>${shelf.books}</td>
<td>
<a href="${pageContext.request.contextPath}/shelf/edit?id=${shelf.id}" class="btn btn-sm btn-primary">
<i class="bi bi-pencil"></i> Edit
</a>
<a href="${pageContext.request.contextPath}/shelf/delete?id=${shelf.id}" class="btn btn-sm btn-danger"
onclick="return confirm('Are you sure you want to delete this shelf?')">
<i class="bi bi-trash"></i> Delete
</a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>