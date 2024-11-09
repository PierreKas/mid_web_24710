package controller;

import model.Book;
import model.Book_status;
import model.Shelf;
import service.BookService;
import service.ShelfService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/book/*")
public class BookServlet extends HttpServlet {
    private BookService bookService;
    private ShelfService shelfService;

    public void init() {
        bookService = new BookService();
        shelfService = new ShelfService();
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
                    deleteBook(request, response);
                    break;
                default:
                    listBooks(request, response);
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
                    insertBook(request, response);
                    break;
                case "/update":
                    updateBook(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/book/list");
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/WEB-INF/book/list.jsp").forward(request, response);
    }

//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.getRequestDispatcher("/WEB-INF/book/form.jsp").forward(request, response);
//    }

//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        List<Shelf> shelves = shelfService.getAllShelves();
//        request.setAttribute("shelves", shelves);
//        request.getRequestDispatcher("/WEB-INF/book/form.jsp").forward(request, response);
//    }
    
//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            List<Shelf> shelves = shelfService.getAllShelves();
//            request.setAttribute("shelves", shelves);
//            request.getRequestDispatcher("/WEB-INF/book/form.jsp").forward(request, response);
//        } catch (Exception e) {
//            // Set error message and redirect to list page instead of showing error.jsp
//            request.getSession().setAttribute("errorMessage", "Error loading shelves: " + e.getMessage());
//            response.sendRedirect(request.getContextPath() + "/book/list");
//        }
//    }
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            String id = request.getParameter("id");
//            Book book = bookService.getBookById(UUID.fromString(id));
//            if (book == null) {
//                request.getSession().setAttribute("errorMessage", "Book not found");
//                response.sendRedirect(request.getContextPath() + "/book/list");
//                return;
//            }
//
//            request.setAttribute("book", book);
//            request.getRequestDispatcher("/WEB-INF/book/form.jsp").forward(request, response);
//
//        } catch (Exception e) {
//            request.getSession().setAttribute("errorMessage", "Error loading book: " + e.getMessage());
//            response.sendRedirect(request.getContextPath() + "/book/list");
//        }
//    }
    
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            String id = request.getParameter("id");
//            Book book = bookService.getBookById(UUID.fromString(id));
//            List<Shelf> shelves = shelfService.getAllShelves();
//            
//            if (book == null) {
//                request.getSession().setAttribute("errorMessage", "Book not found");
//                response.sendRedirect(request.getContextPath() + "/book/list");
//                return;
//            }
//
//            request.setAttribute("shelves", shelves);
//            request.setAttribute("book", book);
//            request.getRequestDispatcher("/WEB-INF/book/form.jsp").forward(request, response);
//
//        } catch (Exception e) {
//            request.getSession().setAttribute("errorMessage", "Error loading book: " + e.getMessage());
//            response.sendRedirect(request.getContextPath() + "/book/list");
//        }
//    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Shelf> shelves = shelfService.getAllShelves();
            request.setAttribute("shelves", shelves);
            request.setAttribute("bookStatuses", Book_status.values());  // Add this line
            request.getRequestDispatcher("/WEB-INF/book/form.jsp").forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error loading form data: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/book/list");
        }
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Book book = bookService.getBookById(UUID.fromString(id));
            List<Shelf> shelves = shelfService.getAllShelves();
            
            if (book == null) {
                request.getSession().setAttribute("errorMessage", "Book not found");
                response.sendRedirect(request.getContextPath() + "/book/list");
                return;
            }

            request.setAttribute("shelves", shelves);
            request.setAttribute("bookStatuses", Book_status.values());  // Add this line
            request.setAttribute("book", book);
            request.getRequestDispatcher("/WEB-INF/book/form.jsp").forward(request, response);

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error loading book: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/book/list");
        }
    }
    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Book book = new Book();
            populateBookFromRequest(book, request);
            bookService.createBook(book);

            request.getSession().setAttribute("successMessage", "Book created successfully");
            response.sendRedirect(request.getContextPath() + "/book/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error creating book: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/book/new");
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String id = request.getParameter("id");
            Book book = bookService.getBookById(UUID.fromString(id));

            if (book == null) {
                request.getSession().setAttribute("errorMessage", "Book not found");
                response.sendRedirect(request.getContextPath() + "/book/list");
                return;
            }

            populateBookFromRequest(book, request);
            bookService.updateBook(book);

            request.getSession().setAttribute("successMessage", "Book updated successfully");
            response.sendRedirect(request.getContextPath() + "/book/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error updating book: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/book/edit?id=" + request.getParameter("id"));
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String id = request.getParameter("id");
            bookService.deleteBook(UUID.fromString(id));

            request.getSession().setAttribute("successMessage", "Book deleted successfully");
            response.sendRedirect(request.getContextPath() + "/book/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error deleting book: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/book/list");
        }
    }

//    private void populateBookFromRequest(Book book, HttpServletRequest request) {
//        book.setTitle(request.getParameter("title"));
//        book.setISBNCode(request.getParameter("isbnCode"));
//        book.setEdition(Integer.parseInt(request.getParameter("edition")));
//        book.setPublisher_name(request.getParameter("publisher_name"));
//        book.setPublication_year(Date.valueOf(request.getParameter("publication_year")));
//        book.setStatus(Book_status.valueOf(request.getParameter("status")));
//        // Note: Shelf assignment would need to be handled separately
//    }
//    private void populateBookFromRequest(Book book, HttpServletRequest request) {
//        book.setTitle(request.getParameter("title"));
//        book.setISBNCode(request.getParameter("isbnCode"));
//        book.setEdition(Integer.parseInt(request.getParameter("edition")));
//        book.setPublisher_name(request.getParameter("publisher_name"));
//        book.setPublication_year(Date.valueOf(request.getParameter("publication_year")));
//        book.setStatus(Book_status.valueOf(request.getParameter("status")));
//        
//        // Handle shelf assignment - stores only the ID in the book table
//        String shelfId = request.getParameter("shelf_id");
//        if (shelfId != null && !shelfId.isEmpty()) {
//            Shelf shelf = shelfService.getShelfById(UUID.fromString(shelfId));
//            book.setShelf(shelf);
//        }
//    }
    
    private void populateBookFromRequest(Book book, HttpServletRequest request) {
        try {
            book.setTitle(request.getParameter("title"));
            book.setISBNCode(request.getParameter("isbnCode"));
            book.setEdition(Integer.parseInt(request.getParameter("edition")));
            book.setPublisher_name(request.getParameter("publisher_name"));
            book.setPublication_year(Date.valueOf(request.getParameter("publication_year")));
            
            String statusStr = request.getParameter("status");
            if (statusStr != null && !statusStr.isEmpty()) {
                book.setStatus(Book_status.valueOf(statusStr.trim()));
            }
            
            // Handle shelf assignment with better error handling
            String shelfId = request.getParameter("shelf_id");
            if (shelfId != null && !shelfId.isEmpty()) {
                try {
                    UUID shelfUUID = UUID.fromString(shelfId);
                    Shelf shelf = shelfService.getShelfById(shelfUUID);
                    if (shelf == null) {
                        throw new IllegalArgumentException("Shelf not found with ID: " + shelfId);
                    }
                    book.setShelf(shelf);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid shelf ID format: " + shelfId, e);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error populating book from request: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while populating book from request", e);
        }
    }
}