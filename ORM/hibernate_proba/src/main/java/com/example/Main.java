package com.example;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        
        Session session = null;

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();) {
            
            session = factory.getCurrentSession(); //Se cierra automáticamente al hacer comm
            Transaction tx = session.beginTransaction();

            /* ================= GUARDAR ================= */
            //Crear libro
            Libro libro1 = new Libro("Hibernate para DAM2", "Alumno1",30);
            session.persist(libro1); //Persist hace un insert en la tabla

            //Creamos usuario
            Usuario usuario1 = new Usuario("Xabier","xabier@gmail.com");
            session.persist(usuario1);

            System.out.println("Libro guardado con ID: "+libro1.getId());
            System.out.println("Usuario guardado con ID: "+usuario1.getId());

            tx.commit();//Confirmamos la creación

            /* ================= MODIFICAR ================= */
            session = factory.getCurrentSession();
            tx = session.beginTransaction();

            libro1.setAutor("Antonio Machado");

            Libro mergedLibro = (Libro) session.merge(libro1);

            tx.commit(); //Confirmamos la modificación

            System.out.println("Libro1 modificado. Nuevo autor: "+mergedLibro.getAutor());

            /* ================= EMILIMINAR ================= */

            session = factory.getCurrentSession();
            tx = session.beginTransaction();

            session.remove(mergedLibro);

            tx.commit();

            System.out.println("Borrado el Libro1: "+mergedLibro);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}