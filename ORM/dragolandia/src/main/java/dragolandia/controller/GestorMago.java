package dragolandia.controller;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import dragolandia.model.Mago;

public class GestorMago {


    private Session session = null;
    
    /**
     * Método para añadir mago a la BD que devuelve true en caso de haberlo conseguido
     * @param mago
     * @return
     */
    public boolean addMago(String nombre, int vida, int nivelMagia){
        Mago mago = new Mago(nombre,vida,nivelMagia);
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {

            session = factory.getCurrentSession();
            Transaction tx = session.beginTransaction();
            session.persist(mago);
            tx.commit();
            System.out.println("Mago registrado con éxito en la BD con id: "+mago.getId());
            return true;

        } catch (Exception e) {
            System.out.println("No se ha podido añadir el mago: "+e.getMessage());
            return false;
        }
    }

    /**
     * Método que devuelve true en caso de que se haya actualizado la vida del mago después de un ataque
     * @param mago
     * @return
     */
    public boolean updateHP(int id, int hp){
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            
            session = factory.getCurrentSession();
            Transaction tx = session.beginTransaction();
            Mago mago = (Mago) session.get(Mago.class, id);
            mago.setVida(hp);
            Mago magoNuevaHP = (Mago) session.merge(mago);
            tx.commit();

            System.out.println("Nuevo hp: "+magoNuevaHP.getVida());

            return true;

        } catch (Exception e) {
            System.out.println("No se ha podido actualizar la vida del mago: "+e.getMessage());
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
    public boolean updateMageName(int id, String nombre){
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
