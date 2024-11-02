//package controller;
//
//import model.Location;
//import model.Role;
//import model.User;
//import service.UserService;
//import service.LocationService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/UserServlet")
//public class UserServlet extends HttpServlet {
//    private UserService userService;
//    private LocationService locationService;
//
//    @Override
//    public void init() {
//        userService = new UserService();
//        locationService = new LocationService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        req.getRequestDispatcher("/createUserForm.html").forward(req, res);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        // Retrieve form data
//        String password = req.getParameter("password");
//        String username = req.getParameter("username");
//        String role = req.getParameter("role");
//        int villageId = Integer.parseInt(req.getParameter("village"));
//        
//        // Retrieve location
//        Location village = locationService.findLocationById(villageId);
//
//        // Encrypt the password
//        String encryptedPassword = userService.encryptPassword(password);
//        
//        // Create and save user
//        User user = new User(encryptedPassword, Role.valueOf(role), username, village, null, null);
//        userService.saveUser(user);
//        
//        // Redirect or forward to success page
//        res.sendRedirect("registrationSuccess.jsp");
//    }
//}
