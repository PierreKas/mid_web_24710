package controller;

import model.Borrower;
import model.Book;
import model.User;
import service.BorrowerService;
import service.BookService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/borrower/*")
public class BorrowerServlet extends HttpServlet {
    private BorrowerService borrowerService;
    private BookService bookService;
    private UserService userService;

    public void init() {
        borrowerService = new BorrowerService();
        bookService = new BookService();
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
                    deleteBorrower(request, response);
                    break;
                case "/return":
                    showReturnForm(request, response);
                    break;
                default:
                    listBorrowers(request, response);
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
                    insertBorrower(request, response);
                    break;
                case "/update":
                    updateBorrower(request, response);
                    break;
                case "/return":
                    returnBook(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/borrower/list");
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    private void listBorrowers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Borrower> borrowers = borrowerService.getAllBorrowers();
        request.setAttribute("borrowers", borrowers);
        request.getRequestDispatcher("/WEB-INF/borrower/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Load books and users for the form
        request.setAttribute("books", bookService.getAllBooks());
        request.setAttribute("users", userService.getAllUsers());
        request.getRequestDispatcher("/WEB-INF/borrower/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Borrower borrower = borrowerService.getBorrowerById(UUID.fromString(id));
            if (borrower == null) {
                request.getSession().setAttribute("errorMessage", "Borrower not found");
                response.sendRedirect(request.getContextPath() + "/borrower/list");
                return;
            }

            request.setAttribute("borrower", borrower);
            request.setAttribute("books", bookService.getAllBooks());
            request.setAttribute("users", userService.getAllUsers());
            request.getRequestDispatcher("/WEB-INF/borrower/form.jsp").forward(request, response);

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error loading borrower: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/borrower/list");
        }
    }

    private void insertBorrower(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Borrower borrower = new Borrower(
                bookService.getBookById(UUID.fromString(request.getParameter("book_id"))),
                Date.valueOf(request.getParameter("due_date")),
                0, // Initial fine
                UUID.randomUUID(),
                0, // Initial late charge fees
                Date.valueOf(request.getParameter("pickup_date")),
                userService.getUserById(request.getParameter("reader_id")), // Changed this line
                null // Return date will be set when book is returned
            );

            borrowerService.createBorrower(borrower);

            request.getSession().setAttribute("successMessage", "Borrower record created successfully");
            response.sendRedirect(request.getContextPath() + "/borrower/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error creating borrower record: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/borrower/new");
        }
    }

    private void updateBorrower(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String id = request.getParameter("id");
            Borrower borrower = borrowerService.getBorrowerById(UUID.fromString(id));

            if (borrower == null) {
                request.getSession().setAttribute("errorMessage", "Borrower not found");
                response.sendRedirect(request.getContextPath() + "/borrower/list");
                return;
            }

            borrower.setBook(bookService.getBookById(UUID.fromString(request.getParameter("book_id"))));
            borrower.setReader(userService.getUserById(request.getParameter("reader_id"))); 
            //borrower.setReader(userService.getUserById(UUID.fromString(request.getParameter("reader_id"))));
            borrower.setDue_date(Date.valueOf(request.getParameter("due_date")));
            borrower.setPickup_date(Date.valueOf(request.getParameter("pickup_date")));

            borrowerService.updateBorrower(borrower);

            request.getSession().setAttribute("successMessage", "Borrower record updated successfully");
            response.sendRedirect(request.getContextPath() + "/borrower/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error updating borrower record: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/borrower/edit?id=" + request.getParameter("id"));
        }
    }

    private void deleteBorrower(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String id = request.getParameter("id");
            borrowerService.deleteBorrower(UUID.fromString(id));

            request.getSession().setAttribute("successMessage", "Borrower record deleted successfully");
            response.sendRedirect(request.getContextPath() + "/borrower/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error deleting borrower record: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/borrower/list");
        }
    }

    private void showReturnForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Borrower borrower = borrowerService.getBorrowerById(UUID.fromString(id));
        request.setAttribute("borrower", borrower);
        request.getRequestDispatcher("/WEB-INF/borrower/return.jsp").forward(request, response);
    }

    private void returnBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String id = request.getParameter("id");
            Date returnDate = Date.valueOf(request.getParameter("return_date"));
            
            borrowerService.returnBook(UUID.fromString(id), returnDate);

            request.getSession().setAttribute("successMessage", "Book returned successfully");
            response.sendRedirect(request.getContextPath() + "/borrower/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error returning book: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/borrower/return?id=" + request.getParameter("id"));
        }
    }
}