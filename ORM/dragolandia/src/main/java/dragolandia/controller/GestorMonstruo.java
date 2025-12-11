package dragolandia.controller;

import org.hibernate.*;

import dragolandia.model.Mago;
import dragolandia.model.Monstruo;
import dragolandia.model.Tipo;

public class GestorMonstruo {
    
    /**
     * Método para añadir una fila monstruo a la tabla monstruos en la bd
     * @param nombre
     * @param vida
     * @param tipo
     * @param fuerza
     * @return
     */
    public boolean addMonstruo(String nombre, int vida, Tipo tipo, int fuerza){

        if(!validarMonstruo(nombre, vida, tipo)){
            return false;
        }

        Monstruo monstruo = new Monstruo(nombre, vida, tipo, fuerza);

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();
            session.persist(monstruo);
            tx.commit();

            System.out.println("El monstruo se ha registrado con éxito en la bd con id: "+monstruo.getId());

            return true;
        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido registrar el monstruo en la bd: "+e.getMessage());
            return false;
        }

    }

    /**
     * Método para comprobar que los parámetros del monstruo son correctos
     * @param nombre
     * @param vida
     * @param tipo
     * @return
     */
    private boolean validarMonstruo(String nombre, int vida, Tipo tipo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío");
            return false;
        }
        
        if (vida <= 0) {
            System.err.println("La vida debe ser mayor que 0");
            return false;
        }
        
        if (tipo == null) {
            System.err.println("El tipo no puede ser nulo");
            return false;
        }
        
        return true;
    }

    /**
     * Método que modifica el nombre del monstruo seǵun el id aportado
     * Devuelve true/false dependiendo de si ha conseguido su objetivo o no
     * @param id
     * @param nombre
     * @return
     */
    public boolean updateName(int id, String nombre){
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Monstruo monstruo = session.get(Monstruo.class, id);

            if(monstruo == null){
                System.err.println("No se ha encontrado el monstruo con id: "+id);
                return false;
            }

            monstruo.setNombre(nombre);
            tx.commit();

            System.out.println("Nuevo nombre: "+monstruo.getNombre()+".");
            return true;

        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido actualizar el nombre del monstruo: "+e.getMessage());
            return false;
        }
    }

    /**
     * Método que actualiza la vida del monstruo asociado al id
     * Devuelve true/false en función de si lo ha conseguido o no
     * @param id
     * @param vida
     * @return
     */
    public boolean updateVida(int id, int vida){
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Monstruo monstruo = session.get(Monstruo.class, id);
            if(monstruo == null){
                System.err.println("No se ha encontrado el monstruo con id: "+id);
                return false;
            }

            monstruo.setVida(vida);
            tx.commit();

            System.out.println("HP restante: "+monstruo.getVida());
            return true;

        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido actualizar el HP del monstruo: "+e.getMessage());
            return false;
        }
    }

    /**
     * Método que actualiza la fuerza del monstruo asociado al id proporcionado
     * Devuelve true/false si lo ha conseguido o no
     * @param id
     * @param fuerza
     * @return
     */
    public boolean updateFuerza(int id, int fuerza){
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tx = session.beginTransaction();

            Monstruo monstruo = session.get(Monstruo.class, id);

            if(monstruo==null){
                System.err.println("No se ha encontrado el monstruo con id: "+id);
                return false;
            }

            monstruo.setFuerza(fuerza);
            tx.commit();

            System.out.println("Nuevo nivel de fuerza: "+monstruo.getFuerza());
            return true;

        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            System.err.println("No se ha podido actualizar la fuerza del monstruo: "+e.getMessage());
            return false;
        }
    }
}
