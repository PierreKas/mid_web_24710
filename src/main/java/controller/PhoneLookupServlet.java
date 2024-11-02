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
//@WebServlet("/lookupPhone")
//public class PhoneLookupServlet extends HttpServlet {
//    private UserService userService;
//
//    @Override
//    public void init() {
//        userService = new UserService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Display the phone lookup form
//        RequestDispatcher dispatcher = request.getRequestDispatcher("PhoneLookup.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String phoneNumber = request.getParameter("phoneNumber");
//
//        String province = userService.getProvinceByPhoneNumber(phoneNumber);
//
//        if (province != null) {
//            request.setAttribute("province", province);
//        } else {
//            request.setAttribute("error", "Phone number not found.");
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("PhoneLookup.jsp");
//        dispatcher.forward(request, response);
//    }
//}
//
