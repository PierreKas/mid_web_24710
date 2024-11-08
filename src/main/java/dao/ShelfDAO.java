//package dao;
//
//
//
//import model.Shelf;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import java.util.List;
//import java.util.UUID;
//import util.HibernateUtil;
//
//public class ShelfDAO {
//
//    public void saveShelf(Shelf shelf) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSession().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(shelf);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    public void updateShelf(Shelf shelf) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSession().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(shelf);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    public Shelf getShelf(UUID id) {
//        try (Session session = HibernateUtil.getSession().openSession()) {
//            return session.get(Shelf.class, id);
//        }
//    }
//
//    public List<Shelf> getAllShelves() {
//        try (Session session = HibernateUtil.getSession().openSession()) {
//            return session.createQuery("from Shelf", Shelf.class).list();
//        }
//    }
//
//    public void deleteShelf(UUID id) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSession().openSession()) {
//            transaction = session.beginTransaction();
//            Shelf shelf = session.get(Shelf.class, id);
//            if (shelf != null) {
//                session.delete(shelf);
//                transaction.commit();
//            }
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//}
