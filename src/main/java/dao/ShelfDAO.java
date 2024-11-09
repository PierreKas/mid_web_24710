package dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Shelf;
import util.HibernateUtil;

public class ShelfDAO {
    public void saveShelf(Shelf shelf) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(shelf);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving shelf: " + e.getMessage(), e);
        }
    }

    public Shelf getShelfById(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Shelf.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error getting shelf by ID: " + e.getMessage(), e);
        }
    }

    public List<Shelf> getAllShelves() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Shelf", Shelf.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Error getting all shelves: " + e.getMessage(), e);
        }
    }

    public void deleteShelf(Shelf shelf) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.delete(shelf);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting shelf: " + e.getMessage(), e);
        }
    }
}