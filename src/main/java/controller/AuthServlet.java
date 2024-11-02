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
//@WebServlet("/login")
//public class AuthServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Display the login form
//        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//
//        boolean isAuthenticated = userService.authenticateUser(userName, password);
//
//        if (isAuthenticated) {
//            response.sendRedirect("dashboard.jsp");
//        } else {
//            request.setAttribute("error", "Invalid credentials.");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
//            dispatcher.forward(request, response);
//        }
//    }
//}
