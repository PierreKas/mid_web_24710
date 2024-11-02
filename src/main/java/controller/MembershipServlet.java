//package controller;
//
//import java.io.IOException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import service.MembershipService;
//
//@WebServlet("/borrowBook")
//public class MembershipServlet extends HttpServlet {
//    private MembershipService membershipService;
//
//    @Override
//    public void init() {
//        membershipService = new MembershipService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Display the borrowing form
//        RequestDispatcher dispatcher = request.getRequestDispatcher("Membership.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userId = request.getParameter("userId");
//        String bookId = request.getParameter("bookId");
//
//        boolean canBorrow = membershipService.canUserBorrowBook(userId);
//
//        if (canBorrow) {
//            membershipService.borrowBook(userId, bookId);
//            request.setAttribute("message", "Book borrowed successfully.");
//        } else {
//            request.setAttribute("error", "Borrowing limit reached for your membership type.");
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("Membership.jsp");
//        dispatcher.forward(request, response);
//    }
//}
//
