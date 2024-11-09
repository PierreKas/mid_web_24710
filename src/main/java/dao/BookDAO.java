package dao;

import model.Book;
import model.Book_status;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;
import java.util.UUID;

public class BookDAO {
    
    // Create or Update a book
    public void saveBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get book by ID
    public Book getBookById(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Book.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get all books
//    public List<Book> getAllBooks() {
//        try (Session session = HibernateUtil.getSession().openSession()) {
//            return session.createQuery("FROM Book", Book.class).list();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public List<Book> getAllBooks() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            System.out.println("BookDAO: Opening session successful");
            List<Book> books = session.createQuery("FROM Book", Book.class).list();
            System.out.println("BookDAO: Query executed, found " + 
                (books != null ? books.size() : 0) + " books");
            return books;
        } catch (Exception e) {
            System.err.println("BookDAO: Error in getAllBooks(): " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // Delete a book
    public void deleteBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get book by ISBN
    public Book getBookByISBN(String isbnCode) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Book WHERE ISBNCode = :isbn", Book.class)
                    .setParameter("isbn", isbnCode)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get books by title
    public List<Book> getBooksByTitle(String title) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Book WHERE title LIKE :title", Book.class)
                    .setParameter("title", "%" + title + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get books by status
    public List<Book> getBooksByStatus(Book_status status) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Book WHERE status = :status", Book.class)
                    .setParameter("status", status)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}