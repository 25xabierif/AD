package com.lambonadas.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    
    private HibernateUtil(){}

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lambonadasServizo");

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void shutdown(){
        if(emf.isOpen()){
            emf.close();
        }
    }

}
