package dao;

import model.Location;
import model.Location_type;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class LocationDAO {
    
    // Create or Update a location
    public void saveLocation(Location location) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get location by ID
    public Location getLocationById(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Location.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get all locations
    public List<Location> getAllLocations() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Location", Location.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Delete a location
    public void deleteLocation(Location location) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.delete(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get locations by type
    public List<Location> getLocationsByType(Location_type type) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Location WHERE locationType = :type", Location.class)
                    .setParameter("type", type)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get child locations by parent ID
    public List<Location> getChildLocations(UUID parentId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Location WHERE parentId = :parentId", Location.class)
                    .setParameter("parentId", parentId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get location by code
    public Location getLocationByCode(String code) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Location WHERE locationCode = :code", Location.class)
                    .setParameter("code", code)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Update location name
    public void updateLocationName(UUID locationId, String newName) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Location location = session.get(Location.class, locationId);
            if (location != null) {
                location.setLocationName(newName);
                session.update(location);
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