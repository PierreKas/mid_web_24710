package dao;



import model.Borrower;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.UUID;
import util.HibernateUtil;

public class BorrowerDAO {

    public void saveBorrower(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(borrower);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateBorrower(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.update(borrower);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Borrower getBorrower(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Borrower.class, id);
        }
    }

    public List<Borrower> getAllBorrowers() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("from Borrower", Borrower.class).list();
        }
    }

    public void deleteBorrower(UUID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Borrower borrower = session.get(Borrower.class, id);
            if (borrower != null) {
                session.delete(borrower);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

