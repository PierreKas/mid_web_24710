package controller;

import service.LocationService;
import model.Location;
import model.Location_type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/location/*")
public class LocationServlet extends HttpServlet {
    private LocationService locationService;

    public void init() {
        locationService = new LocationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteLocation(request, response);
                    break;
                default:
                    listLocations(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/insert":
                    insertLocation(request, response);
                    break;
                case "/update":
                    updateLocation(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/location/list");
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    private void listLocations(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Location> locations = locationService.getAllLocations();
        request.setAttribute("locations", locations);
        request.getRequestDispatcher("/WEB-INF/location/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Location_type selectedType = null;
        String typeParam = request.getParameter("type");
        if (typeParam != null && !typeParam.isEmpty()) {
            selectedType = Location_type.valueOf(typeParam);
        }
        
        request.setAttribute("locationTypes", Location_type.values());
        if (selectedType != null) {
            request.setAttribute("possibleParents", locationService.getPossibleParents(selectedType));
        }
        request.getRequestDispatcher("/WEB-INF/location/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Location location = locationService.getLocationById(UUID.fromString(id));
            if (location == null) {
                request.getSession().setAttribute("errorMessage", "Location not found");
                response.sendRedirect(request.getContextPath() + "/location/list");
                return;
            }
            
            request.setAttribute("location", location);
            request.setAttribute("locationTypes", Location_type.values());
            request.setAttribute("possibleParents", 
                locationService.getPossibleParents(location.getLocationType()));
            request.getRequestDispatcher("/WEB-INF/location/form.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error loading location: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/location/list");
        }
    }

    private void insertLocation(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        try {
            Location location = new Location();
            populateLocationFromRequest(location, request);
            locationService.createLocation(location);
            
            request.getSession().setAttribute("successMessage", "Location created successfully");
            response.sendRedirect(request.getContextPath() + "/location/list");
            
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error creating location: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/location/new");
        }
    }

    private void updateLocation(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        try {
            String id = request.getParameter("id");
            Location location = locationService.getLocationById(UUID.fromString(id));
            
            if (location == null) {
                request.getSession().setAttribute("errorMessage", "Location not found");
                response.sendRedirect(request.getContextPath() + "/location/list");
                return;
            }
            
            populateLocationFromRequest(location, request);
            locationService.updateLocation(location);
            
            request.getSession().setAttribute("successMessage", "Location updated successfully");
            response.sendRedirect(request.getContextPath() + "/location/list");
            
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error updating location: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/location/edit?id=" + request.getParameter("id"));
        }
    }

    private void deleteLocation(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        try {
            String id = request.getParameter("id");
            locationService.deleteLocation(UUID.fromString(id));
            
            request.getSession().setAttribute("successMessage", "Location deleted successfully");
            response.sendRedirect(request.getContextPath() + "/location/list");
            
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error deleting location: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/location/list");
        }
    }

    private void populateLocationFromRequest(Location location, HttpServletRequest request) {
        String locationName = request.getParameter("locationName");
        String locationCode = request.getParameter("locationCode");
        String locationType = request.getParameter("locationType");
       // String parentId = request.getParameter("parentId");

        location.setLocationName(locationName);
        location.setLocationCode(locationCode);
        location.setLocationType(Location_type.valueOf(locationType));
        
//        if (parentId != null && !parentId.trim().isEmpty()) {
//            location.setParentId(UUID.fromString(parentId));
//        }
    }
}