package com.lambonadas.controller;

import com.lambonadas.model.Producto;
import com.lambonadas.model.Stock;

import jakarta.persistence.EntityManager;

public class StockController {

    /**
     * Método que nos permitirá introducir un nuevo stock en la bd.
     * @param cantidad
     * @param productoId
     * @return
     */
    public boolean addStock(int cantidad, int productoId){
        boolean added = false;

        if(validarStock(cantidad, productoId)){
            try (EntityManager em = HibernateUtil.getEntityManager()) {
                
                try {
                    
                    em.getTransaction().begin();

                    Stock stock = new Stock(cantidad, productoId);

                    em.persist(stock);
                    em.getTransaction().commit();

                    added = true;
                    System.out.println("El stock ha sido registrado en la BD con éxito!");

                } catch (Exception e) {
                    System.err.println("No se ha podido registrar el Stock.");
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
            }
        }

        return added;
    }

    /**
     * Método que nos permitirá modificar la cantidad del stock seleccionado a partir de su id.
     * @param id
     * @param cantidad
     * @return
     */
    public boolean updateCantidad(int id, int cantidad){
        boolean updated = false;

        if(validarCantidad(cantidad)){

            try (EntityManager em = HibernateUtil.getEntityManager()) {
            
                try {

                    em.getTransaction().begin();

                    Stock stock = em.find(Stock.class, id);
                    stock.setCantidad(cantidad);

                    em.merge(stock);
                    em.getTransaction().commit();
                    updated = true;
                    System.out.println("El cantidad del stock ha sido actualizada con éxito. Nueva cantidad: "+cantidad);

                } catch (Exception e) {
                    System.err.println("No se ha podido localizar el stock: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
            }
        }

        return updated;
    }

    /**
     * Método que nos permitirá buscar un stock a partir de su id y eliminarlo de la BD.
     * @param id
     * @return
     */
    public boolean deleteStock(int id){

        boolean deleted = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            try {
                
                em.getTransaction().begin();

                Stock stock = em.find(Stock.class, id);
                em.remove(stock);
                em.getTransaction().commit();

                deleted  = true;

                System.out.println("Stock '"+stock.getId()+"' eliminado con éxito.");

            } catch (Exception e) {
                System.err.println("No se ha podido localizar el stock: "+e.getMessage());
            }

        } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
        }

        return deleted;

    }
    
    /**
     * Método de validación de los parámetros que compondrán las instancias de la clase Stock.
     * Los criterios a seguir son los siguientes:
     * La cantidad no puede ser inferior a 0.
     * El producto relacionado con el Stock debe ser un producto existente en la BD.
     * @param cantidad
     * @param productoId
     * @return
     */
    public boolean validarStock(int cantidad, int productoId){

        boolean valido = true;

        if(!validarCantidad(cantidad)){
            valido = false;
        }

        if(!validarProducto(productoId)){
            valido = false;
        }

        return valido;

    }

    public boolean validarCantidad(int cantidad){
        boolean valido = true;
        if(cantidad < 0){
            valido = false;
            System.out.println("La cantidad introducida no puede ser menor que 0.");
        }
        return valido;
    }

    public boolean validarProducto(int productoId){
        boolean valido = true;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            Producto producto = em.find(Producto.class, productoId
            );
            if(producto == null){
                valido = false;
                System.out.println("El producto introducido no es un producto existente.");
            }

        } catch (Exception e) {
            System.err.println("No se ha podido acceder a la sesión (método validación): "+e.getMessage());
        }

        return valido;
    }

}
