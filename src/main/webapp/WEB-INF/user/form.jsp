<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty user ? 'Create' : 'Edit'} User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="row mb-3">
            <div class="col">
                <h2><i class="bi bi-person"></i> ${empty user ? 'Create' : 'Edit'} User</h2>
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
                <form action="${pageContext.request.contextPath}/user/${empty user ? 'insert' : 'update'}" 
                      method="post" class="needs-validation" novalidate>
                    
                    <c:if test="${not empty user}">
                        <input type="hidden" name="id" value="${user.person_id}">
                    </c:if>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="firstName" class="form-label">First Name</label>
                            <input type="text" class="form-control" id="firstName" name="firstName" 
                                   value="${user.first_name}" required>
                            <div class="invalid-feedback">Please provide a first name.</div>
                        </div>
                        
                        <div class="col-md-6">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" 
                                   value="${user.last_name}" required>
                            <div class="invalid-feedback">Please provide a last name.</div>
                        </div>
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="userName" class="form-label">Username</label>
                            <input type="text" class="form-control" id="userName" name="userName" 
                                   value="${user.user_name}" required>
                            <div class="invalid-feedback">Please provide a username.</div>
                        </div>
                        
                        <div class="col-md-6">
                            <label for="password" class="form-label">Password${not empty user ? ' (Leave blank to keep current)' : ''}</label>
                            <input type="password" class="form-control" id="password" name="password" 
                                   ${empty user ? 'required' : ''}>
                            <div class="invalid-feedback">Please provide a password.</div>
                        </div>
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="role" class="form-label">Role</label>
                            <select class="form-select" id="role" name="role" required>
                                <option value="">Select Role</option>
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role}" ${user.role == role ? 'selected' : ''}>${role}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Please select a role.</div>
                        </div>
                        
                        <div class="col-md-6">
                            <label for="gender" class="form-label">Gender</label>
                            <select class="form-select" id="gender" name="gender" required>
                                <option value="">Select Gender</option>
                                <option value="MALE" ${user.gender == 'MALE' ? 'selected' : ''}>Male</option>
                                <option value="FEMALE" ${user.gender == 'FEMALE' ? 'selected' : ''}>Female</option>
                            </select>
                            <div class="invalid-feedback">Please select a gender.</div>
                        </div>
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="phone" class="form-label">Phone Number</label>
                            <input type="tel" class="form-control" id="phone" name="phone" 
                                   value="${user.phone_number}" required>
                            <div class="invalid-feedback">Please provide a phone number.</div>
                        </div>
                        
                        <div class="col-md-6">
                            <label for="villageId" class="form-label">Village</label>
                            <select class="form-select" id="villageId" name="villageId" required>
                                <option value="">Select Village</option>
                                <c:forEach items="${villages}" var="village">
                                    <option value="${village.locationId}" 
                                            ${user.village.locationId == village.locationId ? 'selected' : ''}>
                                        ${village.locationName}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Please select a village.</div>
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <a href="${pageContext.request.contextPath}/user/list" class="btn btn-secondary">
                            <i class="bi bi-arrow-left"></i> Back to List
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-save"></i> Save User
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