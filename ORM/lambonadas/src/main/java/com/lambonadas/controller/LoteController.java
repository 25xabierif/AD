package com.lambonadas.controller;

import java.sql.Date;

import com.lambonadas.model.Lote;
import com.lambonadas.model.Producto;

import jakarta.persistence.EntityManager;

public class LoteController {
    
    public boolean addLote(int cantidad, Date fechaEntrada, Date fechaCaducidad, int productoId){

        boolean added = false;

        if(validarLote(cantidad, fechaEntrada, fechaCaducidad, productoId)){
            try (EntityManager em = HibernateUtil.getEntityManager()) {
                
                try {
                    
                    em.getTransaction().begin();

                    Lote lote = new Lote(cantidad, fechaEntrada, fechaCaducidad, productoId);

                    em.persist(lote);

                    em.getTransaction().commit();

                    added = true;
                    System.out.println("Lote añadido a la BD con éxito!");

                } catch (Exception e) {
                    System.err.println("No se ha podido registrar el nuevo lote: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido acceder a la sesión.");
            }
        }

        return added;
    }

    /**
     * Método que emplearemos para comprobar que las variables que compondrán el lote son válidas.
     * Los criterios a seguir son los siguientes:
     * La cantidad no puede ser inferior a 0.
     * La fecha de caducidad no puede ser anterior a la fecha de entrada.
     * El producto al que pertenece tiene que ser un producto existente.
     * @param cantidad
     * @param fechaEntrada
     * @param fechaCaducidad
     * @param productoId
     * @return
     */
    public boolean validarLote(int cantidad, Date fechaEntrada, Date fechaCaducidad, int productoId){
        boolean valido = true;

        if(!validarCantidad(cantidad)){
            valido = false;
        }

        if(!validarFecha(fechaEntrada, fechaCaducidad)){
            valido = false;
        }
        
        if(!validarProductoId(productoId)){
            valido = false;
        }

        return valido;
    }

    public boolean validarCantidad(int cantidad){
        boolean valido = true;

        if(cantidad < 0){
            valido = false;
            System.out.println("La cantidad no puede ser inferior a 0.");
        }

        return valido;
    }

    public boolean validarProductoId(int productoId){
        boolean valido = true;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            Producto producto = em.find(Producto.class, productoId);
            if(producto == null){
                valido = false;
                System.out.println("El producto introducido no es un producto existente.");
            }

        } catch (Exception e) {
            System.err.println("No se ha podido acceder a la sesión (método validación): "+e.getMessage());
        }

        return valido;
    }

    public boolean validarFecha(Date fechaEntrada, Date fechaCaducidad){
        boolean valido = true;
        if(fechaCaducidad.before(fechaEntrada)){
            valido = false;
            System.out.println("La fecha de caducidad no puede ser anterior a la fecha de entrada.");
        }
        return valido;
    }
}
