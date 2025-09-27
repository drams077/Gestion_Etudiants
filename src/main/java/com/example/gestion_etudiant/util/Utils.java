package com.example.gestion_etudiant.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utils {
    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("persistence");
        } catch (Throwable ex) {
            System.err.println("Erreur lors de la création de l'EntityManagerFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Fournir un EntityManager
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Fermer proprement l’usine
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
