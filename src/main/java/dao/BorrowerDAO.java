package dao;

import model.Borrower;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class BorrowerDAO {
    
    // Create or Update a borrower
    public void saveBorrower(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(borrower);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get borrower by ID
    public Borrower getBorrowerById(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Borrower.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get all borrowers
    public List<Borrower> getAllBorrowers() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Borrower", Borrower.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Delete a borrower
    public void deleteBorrower(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.delete(borrower);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get borrowers by reader ID
    public List<Borrower> getBorrowersByReaderId(UUID readerId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Borrower WHERE reader.id = :readerId", Borrower.class)
                    .setParameter("readerId", readerId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get borrowers by book ID
    public List<Borrower> getBorrowersByBookId(UUID bookId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Borrower WHERE book.id = :bookId", Borrower.class)
                    .setParameter("bookId", bookId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}