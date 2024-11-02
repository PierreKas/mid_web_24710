package dao;



import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import util.HibernateUtil;

public class UserDAO {

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public User getUser(String id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(User.class, id);
        }
    }

    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public void deleteUser(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

