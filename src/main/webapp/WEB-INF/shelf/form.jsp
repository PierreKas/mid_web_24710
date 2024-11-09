<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>${empty shelf ? 'Create' : 'Edit'} Shelf</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
<div class="row mb-3">
<div class="col">
<h2><i class="bi bi-bookshelf"></i> ${empty shelf ? 'Create' : 'Edit'} Shelf</h2>
</div>
</div>

<c:if test="${not empty sessionScope.errorMessage}">
<div class="alert alert-danger alert-dismissible fade show" role="alert">
${sessionScope.errorMessage}
<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<c:remove var="errorMessage" scope="session" />
</c:if>

<div class="card">
<div class="card-body">
<form action="${pageContext.request.contextPath}/shelf/${empty shelf ? 'insert' : 'update'}"
method="post" class="needs-validation" novalidate>

<c:if test="${not empty shelf}">
<input type="hidden" name="id" value="${shelf.id}">
</c:if>

<div class="row mb-3">
<div class="col-md-6">
<label for="book_category" class="form-label">Book Category</label>
<input type="text" class="form-control" id="book_category" name="book_category"
value="${shelf.book_category}" required>
<div class="invalid-feedback">Please provide a book category.</div>
</div>
<div class="col-md-6">
<label for="room_id" class="form-label">Room ID</label>
<input type="text" class="form-control" id="room_id" name="room_id"
value="${shelf.room_id}" required>
<div class="invalid-feedback">Please provide a room ID.</div>
</div>
</div>

<div class="row mb-3">
<div class="col-md-6">
<label for="available_stock" class="form-label">Available Stock</label>
<input type="number" class="form-control" id="available_stock" name="available_stock"
value="${shelf.available_stock}" required>
<div class="invalid-feedback">Please provide the available stock.</div>
</div>
<div class="col-md-6">
<label for="borrowed_number" class="form-label">Borrowed Number</label>
<input type="number" class="form-control" id="borrowed_number" name="borrowed_number"
value="${shelf.borrowed_number}" required>
<div class="invalid-feedback">Please provide the borrowed number.</div>
</div>
</div>

<div class="row mb-3">
<div class="col-md-6">
<label for="initial_stock" class="form-label">Initial Stock</label>
<input type="number" class="form-control" id="initial_stock" name="initial_stock"
value="${shelf.initial_stock}" required>
<div class="invalid-feedback">Please provide the initial stock.</div>
</div>
</div>

<div class="mb-3">
<a href="${pageContext.request.contextPath}/shelf/list" class="btn btn-secondary">
<i class="bi bi-arrow-left"></i> Back to List
</a>
<button type="submit" class="btn btn-primary">
<i class="bi bi-save"></i> Save Shelf
</button>
</div>
</form>
</div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
// Form validation
(function () {
'use strict'
var forms = document.querySelectorAll('.needs-validation')
Array.prototype.slice.call(forms).forEach(function (form) {
form.addEventListener('submit', function (event) {
if (!form.checkValidity()) {
event.preventDefault()
event.stopPropagation()
}
form.classList.add('was-validated')
}, false)
})
})()
</script>
</body>
</html>