package com.example.gestion_etudiant.dao;

import com.example.gestion_etudiant.model.Personne;
import com.example.gestion_etudiant.util.Utils;

import javax.persistence.EntityManager;

public class PersonneDAO {
    public static void save(Personne personne) {
        EntityManager em = Utils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(personne);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void update(Personne personne) {
        EntityManager em = Utils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(personne);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void delete(Personne personne) {
        EntityManager em = Utils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(personne) ? personne: em.merge(personne));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
