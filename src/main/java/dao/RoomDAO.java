package dao;



import model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.UUID;
import util.HibernateUtil;

public class RoomDAO {

    public void saveRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Room getRoom(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Room.class, id);
        }
    }

    public List<Room> getAllRooms() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("from Room", Room.class).list();
        }
    }

    public void deleteRoom(UUID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Room room = session.get(Room.class, id);
            if (room != null) {
                session.delete(room);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

