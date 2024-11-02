package controller;

import java.io.IOException;
import java.util.UUID;
import java.util.List;  // Correct List import

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;  // Added Jackson import

import model.Location;
import dao.LocationDAO;
import model.Location_type;

@WebServlet("/admin/location/*")
public class LocationServlet extends HttpServlet {
    private LocationDAO locationDAO;
    
    @Override
    public void init() {
        locationDAO = new LocationDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // Show location management page
            String typeParam = req.getParameter("type");
            Location_type type = null;

            if (typeParam != null) {
                try {
                    type = Location_type.valueOf(typeParam);
                } catch (IllegalArgumentException e) {
                    // Handle invalid type (return error response or log)
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid location type.");
                    return;
                }
            }

            List<Location> provinces = locationDAO.findByType(type);
            req.setAttribute("provinces", provinces);
            req.getRequestDispatcher("/WEB-INF/admin/locations.jsp").forward(req, resp);
        } else if (pathInfo.equals("/children")) {
            // AJAX endpoint for loading child locations
            String parentId = req.getParameter("parentId");
            if (parentId == null || parentId.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parent ID cannot be null or empty.");
                return;
            }

            List<Location> children = locationDAO.findChildrenByParentId(UUID.fromString(parentId));
            // Return as JSON
            resp.setContentType("application/json");
            new ObjectMapper().writeValue(resp.getOutputStream(), children);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Create new location
        Location location = new Location();  // Now works with default constructor
        location.setLocation_code(req.getParameter("code"));
        location.setLocation_name(req.getParameter("name"));
        location.setType(Location_type.valueOf(req.getParameter("type")));
        
        String parentId = req.getParameter("parentId");
        if (parentId != null && !parentId.isEmpty()) {
            location.setParent(locationDAO.findById(UUID.fromString(parentId)));
        }
        
        locationDAO.saveLocation(location);
        resp.sendRedirect(req.getContextPath() + "/admin/location");
    }
}