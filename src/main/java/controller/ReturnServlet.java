//package controller;
//
//import java.io.IOException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import service.ReturnService;
//
//@WebServlet("/return")
//public class ReturnServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private ReturnService returnService;
//
//    @Override
//    public void init() {
//        returnService = new ReturnService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        RequestDispatcher dispatcher = req.getRequestDispatcher("return.jsp");
//        dispatcher.forward(req, res);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        int bookId = Integer.parseInt(req.getParameter("bookId"));
//        int userId = Integer.parseInt(req.getParameter("userId"));
//
//        double fine = returnService.returnBook(bookId, userId);
//
//        req.setAttribute("fine", fine);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("returnConfirmation.jsp");
//        dispatcher.forward(req, res);
//    }
//}
