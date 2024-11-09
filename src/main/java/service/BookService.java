package service;

import dao.BookDAO;
import model.Book;
import model.Book_status;
import java.util.List;
import java.util.UUID;

public class BookService {
    private BookDAO bookDAO;
    
    public BookService() {
        this.bookDAO = new BookDAO();
    }
    
    public void createBook(Book book) throws Exception {
        // Add business logic validation
        if (book.getISBNCode() == null || book.getISBNCode().trim().isEmpty()) {
            throw new Exception("ISBN code cannot be empty");
        }
        
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new Exception("Book title cannot be empty");
        }
        
        // Check if ISBN already exists
        Book existingBook = bookDAO.getBookByISBN(book.getISBNCode());
        if (existingBook != null) {
            throw new Exception("Book with this ISBN already exists");
        }
        
        // Set default status if not provided
        if (book.getStatus() == null) {
            book.setStatus(Book_status.AVAILABLE);
        }
        
        bookDAO.saveBook(book);
    }
    
    public void updateBook(Book book) throws Exception {
        // Similar validations as create
        if (book.getISBNCode() == null || book.getISBNCode().trim().isEmpty()) {
            throw new Exception("ISBN code cannot be empty");
        }
        
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new Exception("Book title cannot be empty");
        }
        
        // Check if book exists
        Book existingBook = bookDAO.getBookById(book.getBook_id());
        if (existingBook == null) {
            throw new Exception("Book not found");
        }
        
        bookDAO.saveBook(book);
    }
    
    public void deleteBook(UUID bookId) throws Exception {
        Book book = bookDAO.getBookById(bookId);
        if (book != null) {
            bookDAO.deleteBook(book);
        } else {
            throw new Exception("Book not found");
        }
    }
    
    public Book getBookById(UUID id) {
        return bookDAO.getBookById(id);
    }
    
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
    
    public Book getBookByISBN(String isbn) {
        return bookDAO.getBookByISBN(isbn);
    }
    
    public List<Book> getBooksByTitle(String title) {
        return bookDAO.getBooksByTitle(title);
    }
    
    public List<Book> getBooksByStatus(Book_status status) {
        return bookDAO.getBooksByStatus(status);
    }
}