package org.example.dao;

import org.example.entities.ElementoCatalogo;

import javax.persistence.EntityManager;

public class ElementoDAO {

    private EntityManager em;

    public ElementoDAO(EntityManager em) {
        this.em = em;
    }
    //metodi standard del dao
    public void save(ElementoCatalogo e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public ElementoCatalogo getById(long id) {
        return em.find(ElementoCatalogo.class, id);
    }

    public void delete(ElementoCatalogo e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }
    //metodi richiesti dalla traccia dell'esercizio


}

