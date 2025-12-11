package dragolandia.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class GestionDragolandia {
    
    private <T> boolean saveEntity(T entity, String entityName) {
        Transaction tx = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            
            System.out.println(entityName + " registrado con éxito en la BD");
            return true;
            
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("No se ha podido registrar " + entityName + ": " + e.getMessage());
            return false;
        }
    }

}