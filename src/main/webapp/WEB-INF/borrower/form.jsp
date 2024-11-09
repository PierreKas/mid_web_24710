<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty borrower ? 'Create' : 'Edit'} Borrower Record</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="row mb-3">
            <div class="col">
                <h2><i class="bi bi-book"></i> ${empty borrower ? 'Create' : 'Edit'} Borrower Record</h2>
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
                <form action="${pageContext.request.contextPath}/borrower/${empty borrower ? 'insert' : 'update'}" 
                      method="post" class="needs-validation" novalidate>
                    
                    <c:if test="${not empty borrower}">
                        <input type="hidden" name="id" value="${borrower.id}">
                    </c:if>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="book_id" class="form-label">Book</label>
                            <select class="form-select" id="book_id" name="book_id" required>
                                <option value="">Select a book</option>
                                <c:forEach var="book" items="${books}">
                                    <option value="${book.id}" ${borrower.book.id eq book.id ? 'selected' : ''}>
                                        ${book.title}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Please select a book.</div>
                        </div>
                        
                        <div class="col-md-6">
                            <label for="reader_id" class="form-label">Reader</label>
                            <select class="form-select" id="reader_id" name="reader_id" required>
                                <option value="">Select a reader</option>
                                <c:forEach var="user" items="${users}">
                                    <option value="${user.id}" ${borrower.reader.id eq user.id ? 'selected' : ''}>
                                        ${user.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Please select a reader.</div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="pickup_date" class="form-label">Pickup Date</label>
                            <input type="date" class="form-control" id="pickup_date" name="pickup_date" 
                                   value="${borrower.pickup_date}" required>
                            <div class="invalid-feedback">Please select a pickup date.</div>
                        </div>
                        
                        <div class="col-md-6">
                            <label for="due_date" class="form-label">Due Date</label>
                            <input type="date" class="form-control" id="due_date" name="due_date" 
                                   value="${borrower.due_date}" required>
                            <div class="invalid-feedback">Please select a due date.</div>
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <a href="${pageContext.request.contextPath}/borrower/list" class="btn btn-secondary">
                            <i class="bi bi-arrow-left"></i> Back to List
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-save"></i> Save Record
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

        // Ensure due date is after pickup date
        document.getElementById('pickup_date').addEventListener('change', function() {
            document.getElementById('due_date').min = this.value;
        });
    </script>
</body>
</html>