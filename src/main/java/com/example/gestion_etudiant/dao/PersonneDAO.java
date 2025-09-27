package com.example.gestion_etudiant.dao;

import com.example.gestion_etudiant.model.Personne;
import com.example.gestion_etudiant.util.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PersonneDAO {

    public static List<Personne> findAll() {
        EntityManager em = Utils.getEntityManager();
        List<Personne> personnes = null;
        try {
            TypedQuery<Personne> query = em.createQuery("SELECT p FROM Personne p", Personne.class);
            personnes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return personnes;
    }

    public static void save(Personne personne) {
        EntityManager em = Utils.emf.createEntityManager();
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
