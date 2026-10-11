package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

// ORM: membuat SessionFactory Hibernate satu kali dari file hibernate.cfg.xml.
// SessionFactory dipakai untuk membuka Session (pengganti Connection pada JDBC).
public class HibernateUtil {
    private static final Logger LOG_HIBERNATE = Logger.getLogger("org.hibernate");
    private static final SessionFactory FACTORY;

    static {
        // Supaya tampilan konsol tidak penuh log INFO dari Hibernate
        LOG_HIBERNATE.setLevel(Level.WARNING);
        FACTORY = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
