package com.hadiyasa.example_jpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final String PERSISTENCE_NAME = "example_jpa";
    private static EntityManagerFactory entityManagerFactory;

    public static void getEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            getEntityManagerFactory();
        }
        return entityManagerFactory.createEntityManager();
    }

    /** close entityManagerFactory */
    public static void shutDown(){
        /** Cek apakah managerFactory terdapat entity atau tidak */
        if (entityManagerFactory != null){
            entityManagerFactory.close();
            entityManagerFactory = null; // set null
        }
    }
}
