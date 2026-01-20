package com.lambonadas.controller;

import com.lambonadas.model.Categoria;

import jakarta.persistence.EntityManager;

public class CategoriaController {
    
    /**
     * Método que nos permite añadir una nueva categoría a nuestra BD, a partir de un String dado.
     * Devolverá true o false dependiendo de si ha logrado su objetivo o no.
     * @param nombre
     * @return
     */
    public boolean addCategoria(String nombre){
        boolean added = false;

        if(validarCategoria(nombre)){

            try (EntityManager em = HibernateUtil.getEntityManager()) {
            
                try {
                    
                    Categoria categoria = new Categoria(nombre);
                    em.getTransaction().begin();
                    em.persist(categoria);
                    em.getTransaction().commit();

                    added = true;
                    System.out.println("La categoría '"+nombre+"' ha sido añadida con éxito!");
                } catch (Exception e) {
                    System.err.println("No se ha podido añadir la categoría "+nombre+": "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
            }

        }

        return added;
    }

    /**
     * Método que nos permitirá actualizar la variable nombre de la clase categoría en la BD.
     * Devolverá true o false dependiendo de si ha logrado su objetivo o no.
     * @param nombre
     * @return
     */
    public boolean updateName(int id, String nombre){
        boolean updated = false;

        if(validarCategoria(nombre)){
            try (EntityManager em = HibernateUtil.getEntityManager()) {
                
                try {

                    em.getTransaction().begin();

                    Categoria categoria = em.find(Categoria.class, id);

                    if(categoria != null){
                        categoria.setNombre(nombre);

                        em.merge(categoria);
                        em.getTransaction().commit();

                        updated = true;
                        System.out.println("El nombre de la categoría ha sido actualizado con éxito. Nuevo nombre: "+nombre);
                    }else{
                        System.out.println("La categoría indicada no existe. Por favor introduzca un id válido.");
                    }
                    
                } catch (Exception e) {
                    System.err.println("No se ha podido actualizar el nombre de la categoría: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
            }
        }

        return updated;
    }

    /**
     * Método que nos permite buscar una categoría en la BD a través de un id dado y la elimina.
     * Devolverá true o false dependiendo de si ha logrado su objetivo o no.
     * @param id
     * @return
     */
    public boolean removeCategoria(int id){
        boolean deleted = false;
        
        try (EntityManager em = HibernateUtil.getEntityManager()) {

            try {
                
                em.getTransaction().begin();

                Categoria categoria = em.find(Categoria.class, id);

                if(categoria != null){
                    em.remove(categoria);
                    em.getTransaction().commit();

                    deleted = true;
                    System.out.println("Categoría eliminada con éxito");
                }else{
                    System.out.println("El id introducido no se corresponde con una categoría existente.");
                }

            } catch (Exception e) {
                System.err.println("No se ha podido eliminar la categoría: "+e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
        }

        return deleted;
    }

    /**
     * Método que nos permitirá comprobar que las variables que componen la categoría son correctas.
     * Los criterios a seguir son los siguientes:
     * El nombre no puede ser nulo o estar compuesto por una cadena de caracteres vacía.
     * @param nombre
     * @return
     */
    public boolean validarCategoria(String nombre){
        boolean valida = true;
        if(nombre.toLowerCase().trim() == null && nombre.toLowerCase().trim().length() == 0){
            valida = false;
            System.out.println("El nombre introducido no es válido.");
        }
        return valida;
    }

}
