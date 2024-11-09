package util;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import model.Book;
import model.Borrower;
import model.Location;
import model.Room;
import model.Shelf;
import model.User;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    
    public static SessionFactory getSession() {
        if (sessionFactory == null) {
            try {
                Configuration conf = new Configuration();
                Properties settings = new Properties();
                
                // Database connection settings
                settings.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/auca_library_db?createDatabaseIfNotExist=true");
                settings.setProperty(Environment.USER, "pierre");
                settings.setProperty(Environment.PASS, "KASANANI");
                
                // Hibernate settings
                settings.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.setProperty(Environment.SHOW_SQL, "true");
                settings.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.setProperty(Environment.HBM2DDL_AUTO, "update"); // create-drop
                
                // Connection pool settings
                settings.setProperty(Environment.C3P0_MIN_SIZE, "5");
                settings.setProperty(Environment.C3P0_MAX_SIZE, "20");
                settings.setProperty(Environment.C3P0_TIMEOUT, "300");
                
                // Apply settings
                conf.setProperties(settings);
                
                // Add annotated classes
                conf.addAnnotatedClass(Location.class);
                conf.addAnnotatedClass(User.class);
                conf.addAnnotatedClass(Book.class);
                conf.addAnnotatedClass(Borrower.class);
                // conf.addAnnotatedClass(Membership.class);
                // conf.addAnnotatedClass(membership_type.class);
                 conf.addAnnotatedClass(Shelf.class);
                 conf.addAnnotatedClass(Room.class);
                
                sessionFactory = conf.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}