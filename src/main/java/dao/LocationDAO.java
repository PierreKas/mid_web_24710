package dao;

import model.Location;
import model.Location_type;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class LocationDAO {
    public void saveLocation(Location location) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Location findById(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Location.class, id);
        }
    }

    public List<Location> findByType(Location_type type) {
    	if (type == null) {
            throw new IllegalArgumentException("Location_type cannot be null");
        }
    	try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery(
                "FROM Location WHERE type = :type", Location.class)
                .setParameter("type", type)
                .list();
        }
    }

    public List<Location> findChildrenByParentId(UUID parentId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            if (parentId == null) {
                throw new IllegalArgumentException("Parent ID cannot be null");
            }
            return session.createQuery(
                "FROM Location WHERE parent.location_id = :parentId", Location.class)
                .setParameter("parentId", parentId)
                .list();
        }
    }

}