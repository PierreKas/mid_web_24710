package dao;

import model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class RoomDAO {
    
    // Create or Update a room
   
    public void saveRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
//	   public void saveRoom(Room room) {
//	        try (Session session = HibernateUtil.getSession().openSession()) {
//	            Transaction transaction = session.beginTransaction();
//	            session.saveOrUpdate(room);
//	            transaction.commit();
//	        } catch (Exception e) {
//	            throw new RuntimeException("Error saving room: " + e.getMessage(), e);
//	        }
//	    }
    
    // Get room by ID
    public Room getRoomById(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Room.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Get all rooms
    public List<Room> getAllRooms() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Room", Room.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Delete a room
    public void deleteRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.delete(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Get room by code
    public Room getRoomByCode(String code) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Room WHERE room_code = :code", Room.class)
                    .setParameter("code", code)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Update room code
    public void updateRoomCode(UUID roomId, String newCode) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Room room = session.get(Room.class, roomId);
            if (room != null) {
                room.setRoom_code(newCode);
                session.update(room);
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