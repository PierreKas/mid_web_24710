package dao;



import model.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.UUID;
import util.HibernateUtil;

public class BookDAO {

    public void saveBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Book getBook(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Book.class, id);
        }
    }

    public List<Book> getAllBooks() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }

    public void deleteBook(UUID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book != null) {
                session.delete(book);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

