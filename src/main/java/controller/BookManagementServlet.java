//package controller;
//
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
//import service.BookService;
//
//@WebServlet("/manageBook")
//public class BookManagementServlet extends HttpServlet {
//    private BookService bookService;
//
//    @Override
//    public void init() {
//        bookService = new BookService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Display the book management form
//        RequestDispatcher dispatcher = request.getRequestDispatcher("BookManagementForm.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String title = request.getParameter("title");
//        String author = request.getParameter("author");
//        String publisher = request.getParameter("publisher");
//        int year = Integer.parseInt(request.getParameter("year"));
//        String isbn = request.getParameter("isbn");
//
//        boolean isAdded = bookService.addBook(title, author, publisher, year, isbn);
//
//        if (isAdded) {
//            request.setAttribute("message", "Book added successfully!");
//        } else {
//            request.setAttribute("error", "Failed to add book.");
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("BookManagementForm.jsp");
//        dispatcher.forward(request, response);
//    }
//}
//
