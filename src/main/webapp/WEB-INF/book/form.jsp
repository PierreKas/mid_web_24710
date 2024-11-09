<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty book ? 'Create' : 'Edit'} Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="row mb-3">
            <div class="col">
                <h2><i class="bi bi-book"></i> ${empty book ? 'Create' : 'Edit'} Book</h2>
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
                <form action="${pageContext.request.contextPath}/book/${empty book ? 'insert' : 'update'}"
                      method="post" class="needs-validation" novalidate>

                    <c:if test="${not empty book}">
                        <input type="hidden" name="id" value="${book.book_id}">
                    </c:if>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="title" class="form-label">Title</label>
                            <input type="text" class="form-control" id="title" name="title"
                                   value="${book.title}" required>
                            <div class="invalid-feedback">Please provide a book title.</div>
                        </div>

                        <div class="col-md-6">
                            <label for="isbnCode" class="form-label">ISBN Code</label>
                            <input type="text" class="form-control" id="isbnCode" name="isbnCode"
                                   value="${book.ISBNCode}" required>
                            <div class="invalid-feedback">Please provide an ISBN code.</div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-4">
                            <label for="edition" class="form-label">Edition</label>
                            <input type="number" class="form-control" id="edition" name="edition"
                                   value="${book.edition}" required min="1">
                            <div class="invalid-feedback">Please provide a valid edition number.</div>
                        </div>

                        <div class="col-md-4">
                            <label for="publisher_name" class="form-label">Publisher</label>
                            <input type="text" class="form-control" id="publisher_name" name="publisher_name"
                                   value="${book.publisher_name}" required>
                            <div class="invalid-feedback">Please provide a publisher name.</div>
                        </div>

                        <div class="col-md-4">
                            <label for="publication_year" class="form-label">Publication Year</label>
                            <input type="date" class="form-control" id="publication_year" name="publication_year"
                                   value="<fmt:formatDate value="${book.publication_year}" pattern="yyyy-MM-dd"/>" required>
                            <div class="invalid-feedback">Please provide a publication year.</div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="status" class="form-label">Status</label>
                            <select class="form-select" id="status" name="status" required>
                                <c:forEach var="statusOption" items="<%= model.Book_status.values() %>">
                                    <option value="${statusOption}" ${book.status == statusOption ? 'selected' : ''}>
                                        ${statusOption}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Please select a status.</div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <a href="${pageContext.request.contextPath}/book/list" class="btn btn-secondary">
                            <i class="bi bi-arrow-left"></i> Back to List
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-save"></i> Save Book
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Form validation
        (function() {
            'use strict'
            var forms = document.querySelectorAll('.needs-validation')
            Array.prototype.slice.call(forms).forEach(function(form) {
                form.addEventListener('submit', function(event) {
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