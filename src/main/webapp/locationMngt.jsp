<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Location Management</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <div class="container mt-4">
        <h2>Location Management</h2>
        
        <!-- Location Creation Form -->
        <div class="card mb-4">
            <div class="card-header">Add New Location</div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/location" method="post">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>Location Type</label>
                                <select name="type" class="form-control" id="locationType">
                                    <option value="PROVINCE">Province</option>
                                    <option value="DISTRICT">District</option>
                                    <option value="SECTOR">Sector</option>
                                    <option value="CELL">Cell</option>
                                    <option value="VILLAGE">Village</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>Code</label>
                                <input type="text" name="code" class="form-control" required>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" name="name" class="form-control" required>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>Parent Location</label>
                                <select name="parentId" class="form-control" id="parentLocation">
                                    <option value="">Select Parent</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Add Location</button>
                </form>
            </div>
        </div>

        <!-- Location Hierarchy View -->
        <div class="card">
            <div class="card-header">Location Hierarchy</div>
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <h5>Provinces</h5>
                        <ul class="list-group" id="provinceList">
                            <c:forEach items="${provinces}" var="province">
                                <li class="list-group-item" data-id="${province.location_id}">
                                    ${province.location_name}
                                    <button class="btn btn-sm btn-link load-children">Show Districts</button>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="col" id="districtContainer"></div>
                    <div class="col" id="sectorContainer"></div>
                    <div class="col" id="cellContainer"></div>
                    <div class="col" id="villageContainer"></div>
                </div>
            </div>
        </div>
    </div>

  <script>
    $(document).ready(function() {
        // Log the selected location type when it changes
        $('#locationType').change(function() {
            console.log("Selected location type: ", $(this).val());
            
            let type = $(this).val();
            let parentType;
            
            switch(type) {
                case 'DISTRICT': parentType = 'PROVINCE'; break;
                case 'SECTOR': parentType = 'DISTRICT'; break;
                case 'CELL': parentType = 'SECTOR'; break;
                case 'VILLAGE': parentType = 'CELL'; break;
                default: parentType = null;
            }
            
            if (parentType) {
                $.get('${pageContext.request.contextPath}/admin/location/byType', 
                    { type: parentType },
                    function(data) {
                        let options = '<option value="">Select Parent</option>';
                        data.forEach(function(location) {
                            options += `<option value="${location.location_id}">${location.location_name}</option>`;
                        });
                        $('#parentLocation').html(options);
                    });
            }
        });

        // Load child locations
        $('.load-children').click(function() {
            let parentId = $(this).closest('li').data('id');
            let container = $(this).closest('.col').next();
            
            $.get('${pageContext.request.contextPath}/admin/location/children',
                { parentId: parentId },
                function(data) {
                    let html = '<ul class="list-group">';
                    data.forEach(function(location) {
                        html += `<li class="list-group-item" data-id="${location.location_id}">
                            ${location.location_name}
                            <button class="btn btn-sm btn-link load-children">Show Children</button>
                        </li>`;
                    });
                    html += '</ul>';
                    container.html(html);
                });
        });
    });
</script>

</body>
</html>