<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty location ? 'Create' : 'Edit'} Location</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="row mb-3">
            <div class="col">
                <h2><i class="bi bi-geo-alt"></i> ${empty location ? 'Create' : 'Edit'} Location</h2>
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
                <form action="${pageContext.request.contextPath}/location/${empty location ? 'insert' : 'update'}" 
                      method="post" class="needs-validation" novalidate>
                    
                    <c:if test="${not empty location}">
                        <input type="hidden" name="id" value="${location.locationId}">
                    </c:if>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="locationName" class="form-label">Location Name</label>
                            <input type="text" class="form-control" id="locationName" name="locationName" 
                                   value="${location.locationName}" required>
                            <div class="invalid-feedback">Please provide a location name.</div>
                        </div>
                        
                        <div class="col-md-6">
                            <label for="locationCode" class="form-label">Location Code</label>
                            <input type="text" class="form-control" id="locationCode" name="locationCode" 
                                   value="${location.locationCode}" required>
                            <div class="invalid-feedback">Please provide a location code.</div>
                        </div>
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="locationType" class="form-label">Location Type</label>
                            <select class="form-select" id="locationType" name="locationType" required>
                                <option value="">Select Type</option>
                                <option value="PROVINCE" ${location.locationType == 'PROVINCE' ? 'selected' : ''}>Province</option>
                                <option value="DISTRICT" ${location.locationType == 'DISTRICT' ? 'selected' : ''}>District</option>
                                <option value="SECTOR" ${location.locationType == 'SECTOR' ? 'selected' : ''}>Sector</option>
                                <option value="CELL" ${location.locationType == 'CELL' ? 'selected' : ''}>Cell</option>
                                <option value="VILLAGE" ${location.locationType == 'VILLAGE' ? 'selected' : ''}>Village</option>
                            </select>
                            <div class="invalid-feedback">Please select a location type.</div>
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <a href="${pageContext.request.contextPath}/location/list" class="btn btn-secondary">
                            <i class="bi bi-arrow-left"></i> Back to List
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-save"></i> Save Location
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