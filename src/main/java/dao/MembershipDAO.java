package dao;

import model.Membership;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.UUID;
import util.HibernateUtil;

public class MembershipDAO {

    public void saveMembership(Membership membership) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(membership);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateMembership(Membership membership) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.update(membership);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Membership getMembership(UUID id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Membership.class, id);
        }
    }

    public List<Membership> getAllMemberships() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("from Membership", Membership.class).list();
        }
    }

    public void deleteMembership(UUID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Membership membership = session.get(Membership.class, id);
            if (membership != null) {
                session.delete(membership);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

