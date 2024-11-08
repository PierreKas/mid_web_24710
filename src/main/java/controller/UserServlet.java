package controller;

import service.UserService;
import model.User;
import model.Role;
import model.Location;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        userService = new UserService();
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
                    deleteUser(request, response);
                    break;
                default:
                    listUsers(request, response);
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
                    insertUser(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/user/list");
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/user/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("roles", Role.values());
        request.getRequestDispatcher("/WEB-INF/user/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            User user = userService.getUserById(id);
            if (user == null) {
                request.getSession().setAttribute("errorMessage", "User not found");
                response.sendRedirect(request.getContextPath() + "/user/list");
                return;
            }
            
            request.setAttribute("user", user);
            request.setAttribute("roles", Role.values());
            request.getRequestDispatcher("/WEB-INF/user/form.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error loading user: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/user/list");
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        try {
            User user = new User();
            populateUserFromRequest(user, request);
            userService.createUser(user);
            
            request.getSession().setAttribute("successMessage", "User created successfully");
            response.sendRedirect(request.getContextPath() + "/user/list");
            
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error creating user: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/user/new");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        try {
            String id = request.getParameter("id");
            User user = userService.getUserById(id);
            
            if (user == null) {
                request.getSession().setAttribute("errorMessage", "User not found");
                response.sendRedirect(request.getContextPath() + "/user/list");
                return;
            }
            
            populateUserFromRequest(user, request);
            userService.updateUser(user);
            
            request.getSession().setAttribute("successMessage", "User updated successfully");
            response.sendRedirect(request.getContextPath() + "/user/list");
            
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error updating user: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/user/edit?id=" + request.getParameter("id"));
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        try {
            String id = request.getParameter("id");
            userService.deleteUser(id);
            
            request.getSession().setAttribute("successMessage", "User deleted successfully");
            response.sendRedirect(request.getContextPath() + "/user/list");
            
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error deleting user: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/user/list");
        }
    }

    private void populateUserFromRequest(User user, HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String villageIdStr = request.getParameter("villageId");
        UUID villageId = null;

        try {
            if (villageIdStr != null) {
                villageId = UUID.fromString(villageIdStr);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format: " + villageIdStr);
            // Handle invalid UUID format here
        }

        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setUser_name(userName);
        // Only set password if it's provided (for updates)
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password);
        }
        user.setRole(Role.valueOf(role));
        user.setPhone_number(phone);
        user.setGender(model.Gender.valueOf(gender));

        // Set village if provided
        if (villageId != null) {
            Location village = new Location();
            village.setLocationId(villageId);
            user.setVillage(village);
        }
    }
}