package dragolandia.controller;

import org.hibernate.*;

import dragolandia.model.Mago;

public class GestorMago {
    
    /**
     * Método para añadir mago a la BD que devuelve true en caso de haberlo conseguido
     * @param mago
     * @return
     */
    public boolean addMago(String nombre, int vida, int nivelMagia){
        Mago mago = new Mago(nombre,vida,nivelMagia);
        
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();
            session.persist(mago);
            tx.commit();

            System.out.println("Mago registrado con éxito en la BD con id: "+mago.getId()+".");
            return true;

        } catch (Exception e) {
            if(tx != null) tx.rollback();
            System.err.println("No se ha podido registrar el mago en la BD: "+e.getMessage());
            return false;
        }

    }

    /**
     * Método que devuelve true en caso de que se haya actualizado la vida del mago después de un ataque
     * @param mago
     * @return
     */
    public boolean updateHP(int id, int hp){
        
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Mago mago = session.get(Mago.class, id);

            if(mago == null){
                System.err.println("No se encontró el mago con id: "+id);
                return false;
            }

            mago.setVida(hp);

            tx.commit();
            System.out.println("HP actual: "+mago.getVida());
            return true;

        } catch (Exception e) {
            if(tx != null) tx.rollback();
            System.err.println("No se ha podido actualizar la vida del mago: "+e.getMessage());
            return false;
        }

    }

    /**
     * Metodo que selecciona un mago por su id y le actualiza el nombre
     * Devuelve true/false en función de si lo ha conseguido o no
     * @param id
     * @param nombre
     * @return
     */
    public boolean updateName(int id, String nombre){
        
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Mago mago = session.get(Mago.class, id);
            if(mago == null){
                System.err.println("No se encontró el mago con id: "+id);
                return false;
            }

            mago.setNombre(nombre);

            tx.commit();
            System.out.println("Nuevo nombre: "+mago.getNombre()+".");
            return true;

        } catch (Exception e) {
            if(tx != null) tx.rollback();
            System.err.println("No se ha podido actualizar el nombre del mago: "+e.getMessage());
            return false;
        }

    }

    /**
     * Método que selecciona un mago por su id y le actualiza el nivel de magia
     * Devuelve true/false en caso de que la operación haya sido realizada con éxito
     * @param id
     * @param nivelMagia
     * @return
     */
    public boolean updateMagicLevel(int id, int nivelMagia){
        
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Mago mago = session.get(Mago.class, id);

            if(mago == null){
                System.err.println("No se encontró el mago con id: "+id);
                return false;
            }

            mago.setNivelMagia(nivelMagia);

            tx.commit();
            System.out.println("Nuevo nivel de magia: "+mago.getNivelMagia()+".");
            return true;

        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido actualizar el nivel de magia del mago: "+e.getMessage());
            return false;
        }

    }

}
