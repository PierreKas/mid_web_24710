package dao;

import model.User;
import model.Role;
import model.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class UserDAO {
    
    // Create or Update a user
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get user by ID
    public User getUserById(String string) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(User.class, string);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get all users
//    public List<User> getAllUsers() {
//        try (Session session = HibernateUtil.getSession().openSession()) {
//            return session.createQuery("FROM User", User.class).list();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            System.out.println("UserDAO: Opening session successful");
            List<User> users = session.createQuery("FROM User", User.class).list();
            System.out.println("UserDAO: Query executed, found " + 
                (users != null ? users.size() : 0) + " users");
            return users;
        } catch (Exception e) {
            System.err.println("UserDAO: Error in getAllUsers(): " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // Delete a user
    public void deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get user by username
    public User getUserByUsername(String username) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM User WHERE user_name = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get users by role
    public List<User> getUsersByRole(Role role) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM User WHERE role = :role", User.class)
                    .setParameter("role", role)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get users by village/location
    public List<User> getUsersByVillage(Location village) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM User WHERE village = :village", User.class)
                    .setParameter("village", village)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Update user password
    public void updateUserPassword(String userId, String newPassword) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                user.setPassword(newPassword);
                session.update(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Update user role
    public void updateUserRole(String userId, Role newRole) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                user.setRole(newRole);
                session.update(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}