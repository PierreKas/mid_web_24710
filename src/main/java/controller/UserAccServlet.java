//package controller;
//
//
//import java.io.IOException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import service.UserService;
//
//@WebServlet("/createAccount")
//public class UserAccServlet extends HttpServlet {
//    private UserService userService;
//
//    @Override
//    public void init() {
//        userService = new UserService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Display the account creation form
//        RequestDispatcher dispatcher = request.getRequestDispatcher("UserAccountForm.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String dob = request.getParameter("dob");
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//        String role = request.getParameter("role");
//        String membershipType = request.getParameter("membershipType");
//
//        boolean isCreated = userService.createUser(firstName, lastName, dob, userName, password, role, membershipType);
//
//        if (isCreated) {
//            request.setAttribute("message", "Account created successfully!");
//        } else {
//            request.setAttribute("error", "Failed to create account.");
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("UserAccountForm.jsp");
//        dispatcher.forward(request, response);
//    }
//}
//
