package com.lambonadas.controller;

import com.lambonadas.model.Categoria;
import com.lambonadas.model.Producto;

import jakarta.persistence.EntityManager;

public class ProductoController {
    
    /**
     * Método que nos permitirá registrar un nuevo producto en la BD a partir de una serie de variables.
     * @param nombre
     * @param peso
     * @param precio
     * @param categoriaId
     * @return
     */
    public boolean addProducto(String nombre, int peso, float precio, Categoria categoria){

        boolean added = false;

        if(validarProducto(nombre, peso, precio, categoria)){
            try (EntityManager em = HibernateUtil.getEntityManager()) {
                
                try {
                    
                    em.getTransaction().begin();

                    Producto producto = new Producto(nombre, peso, precio, categoria);

                    em.persist(producto);

                    em.getTransaction().commit();

                    added = true;
                    System.out.println("Producto '"+nombre+"' añadido a la BD con éxito!");

                } catch (Exception e) {
                    System.err.println("No se ha podido registrar el nuevo producto: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión.");
            }
        }

        return added;
    }

    /**
     * Método que nos permitirá modificar el nombre del producto seleccionado a partir de su id.
     * @param id
     * @param nombre
     * @return
     */
    public boolean updateName(int id, String nombre){
        boolean updated = false;

        if(validarNombre(nombre)){

            try (EntityManager em = HibernateUtil.getEntityManager()) {
            
                try {

                    em.getTransaction().begin();

                    Producto producto = em.find(Producto.class, id);
                    producto.setNombre(nombre);

                    em.merge(producto);
                    em.getTransaction().commit();
                    updated = true;
                    System.out.println("El nombre del producto ha sido actualizado con éxito. Nuevo nombre: "+nombre);

                } catch (Exception e) {
                    System.err.println("No se ha podido localizar el producto: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
            }
        }

        return updated;
    }

    /**
     * Método que nos permitirá modificar el peso del producto seleccionado a partir de su id.
     * @param id
     * @param peso
     * @return
     */
    public boolean updatePeso(int id, int peso){
        boolean updated = false;

        if(validarPeso(peso)){

            try (EntityManager em = HibernateUtil.getEntityManager()) {
            
                try {

                    em.getTransaction().begin();

                    Producto producto = em.find(Producto.class, id);
                    producto.setPeso(peso);

                    em.merge(producto);
                    em.getTransaction().commit();
                    updated = true;
                    System.out.println("El peso del producto ha sido actualizado con éxito. Nuevo peso: "+peso);

                } catch (Exception e) {
                    System.err.println("No se ha podido localizar el producto: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
            }
        }

        return updated;
    }

    /**
     * Método que nos permitirá modificar el precio del producto seleccionado a partir de su id.
     * @param id
     * @param precio
     * @return
     */
    public boolean updateName(int id, float precio){
        boolean updated = false;

        if(validarPrecio(precio)){

            try (EntityManager em = HibernateUtil.getEntityManager()) {
            
                try {

                    em.getTransaction().begin();

                    Producto producto = em.find(Producto.class, id);
                    producto.setPrecio(precio);

                    em.merge(producto);
                    em.getTransaction().commit();
                    updated = true;
                    System.out.println("El precio del producto ha sido actualizado con éxito. Nuevo precio: "+precio);

                } catch (Exception e) {
                    System.err.println("No se ha podido localizar el producto: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
            }
        }

        return updated;
    }

    /**
     * Método que nos permitirá modificar la categoría del producto seleccionado a partir de su id.
     * @param id
     * @param categoría
     * @return
     */
    public boolean updateCategoria(int id, Categoria categoria){
        boolean updated = false;

        if(validarCategoria(categoria)){

            try (EntityManager em = HibernateUtil.getEntityManager()) {
            
                try {

                    em.getTransaction().begin();

                    Producto producto = em.find(Producto.class, id);
                    producto.setCategoria(categoria);

                    em.merge(producto);
                    em.getTransaction().commit();
                    updated = true;
                    System.out.println("El categoría del producto ha sido actualizada con éxito. Nueva categoría: "+categoria.getNombre());

                } catch (Exception e) {
                    System.err.println("No se ha podido localizar el producto: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
            }
        }

        return updated;
    }

    /**
     * Método que nos permitirá buscar un producto a partir de su id y eliminarlo de la BD.
     * @param id
     * @return
     */
    public boolean deleteProducto(int id){

        boolean deleted = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            try {
                
                em.getTransaction().begin();

                Producto producto = em.find(Producto.class, id);
                em.remove(producto);
                em.getTransaction().commit();

                deleted  = true;

                System.out.println("Producto '"+producto.getNombre()+"' eliminado con éxito.");

            } catch (Exception e) {
                System.err.println("No se ha podido localizar el producto: "+e.getMessage());
            }

        } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
        }

        return deleted;

    }

    /**
     * Método que emplearemos para comprobar que las variables que compondrán el producto son válidas.
     * Los criterios a seguir son los siguientes:
     * El nombre no puede ser nulo o estar compuesto por una cadena de caracteres vacía.
     * El peso no puede ser menor o igual a 0.
     * El precio no puede ser menor que 0.0.
     * La categoría a la que pertenece tiene que ser una categoría existente.
     * @param nombre
     * @param peso
     * @param precio
     * @param categoriaId
     * @return
     */
    public boolean validarProducto(String nombre, int peso, float precio, Categoria categoria){
        boolean valido = true;

        if(!validarNombre(nombre)){
            valido = false;
        }

        if(!validarPeso(peso)){
            valido = false;
        }

        if(!validarPrecio(precio)){
            valido = false;
        }

        if(!validarCategoria(categoria)){
            valido = false;
        }

        return valido;
    }

    public boolean validarNombre(String nombre){
        boolean valido = true;
        if(nombre.toLowerCase().trim() == null || nombre.toLowerCase().trim().length() == 0){
            valido = false;
            System.out.println("El nombre introducido no es válido.");
        }
        return valido;
    }

    public boolean validarPeso(int peso){
        boolean valido = true;
        if(peso <= 0){
            valido = false;
            System.out.println("El peso introducido no puede ser menor o igual a 0.");
        }
        return valido;
    }

    public boolean validarPrecio(float precio){
        boolean valido = true;
        if(precio < 0.0){
            valido = false;
            System.out.println("El precio introducido no puede ser menor que 0.");
        }
        return valido;
    }

    public boolean validarCategoria(Categoria categoria){
        boolean valido = true;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            Categoria categoriaFind = em.find(Categoria.class, categoria.getId());
            if(categoriaFind == null){
                valido = false;
                System.out.println("La categoría introducida no es una categoría existente.");
            }

        } catch (Exception e) {
            System.err.println("No se ha podido acceder a la sesión (método validación): "+e.getMessage());
        }

        return valido;
    }
}
