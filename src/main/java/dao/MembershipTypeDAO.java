package dao;

import model.membership_type;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.UUID;
import util.HibernateUtil;

public class MembershipTypeDAO {

    public void saveMembershipType(membership_type membershipType) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(membershipType);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateMembershipType(membership_type membershipType) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.update(membershipType);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public membership_type getMembershipType(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(membership_type.class, id);
        }
    }

    public List<membership_type> getAllMembershipTypes() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("from MembershipType", membership_type.class).list();
        }
    }

    public void deleteMembershipType(UUID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            membership_type membershipType = session.get(membership_type.class, id);
            if (membershipType != null) {
                session.delete(membershipType);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

