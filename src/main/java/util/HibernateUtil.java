package util;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

// Import all model classes
import model.Location;
import model.User;
import model.Book;
import model.Borrower;
import model.Membership;
import model.membership_type;
import model.Shelf;
import model.Room;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory = null;
    
    public static SessionFactory getSession() {
        if(sessionFactory == null) {
            Configuration conf = new Configuration();
            Properties settings = new Properties();
            
            // Database connection settings
            settings.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/auca_library_db");
            settings.setProperty(Environment.USER, "root");
            settings.setProperty(Environment.PASS, "");
            
            // Hibernate settings
            settings.setProperty(Environment.HBM2DDL_AUTO, "update");
            settings.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            settings.setProperty(Environment.SHOW_SQL, "true");
            
            // Apply settings
            conf.setProperties(settings);
            
            // Add annotated classes
            conf.addAnnotatedClass(Location.class);
            conf.addAnnotatedClass(User.class);
            conf.addAnnotatedClass(Book.class);
            conf.addAnnotatedClass(Borrower.class);
            conf.addAnnotatedClass(Membership.class);
            conf.addAnnotatedClass(membership_type.class);
            conf.addAnnotatedClass(Shelf.class);
            conf.addAnnotatedClass(Room.class);
            
            sessionFactory = conf.buildSessionFactory();
        }
        return sessionFactory;
    }
}