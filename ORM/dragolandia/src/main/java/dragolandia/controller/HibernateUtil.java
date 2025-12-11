package dragolandia.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    
    // Constructor privado para evitar instanciación
    private HibernateUtil() {}
    
    // Método para obtener el SessionFactory (lazy initialization)
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                    .configure() // Lee hibernate.cfg.xml
                    .buildSessionFactory();
            } catch (Exception e) {
                System.err.println("Error al crear SessionFactory: " + e.getMessage());
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
    
    // Método para cerrar el SessionFactory cuando termine la aplicación
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}