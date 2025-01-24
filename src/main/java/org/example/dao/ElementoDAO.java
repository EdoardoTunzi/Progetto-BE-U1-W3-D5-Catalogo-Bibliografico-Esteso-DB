package org.example.dao;

import org.example.entities.ElementoCatalogo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class ElementoDAO {

    private EntityManager em;

    public ElementoDAO(EntityManager em) {
        this.em = em;
    }
    //metodi standard del dao

    public ElementoCatalogo getById(long id) {
        return em.find(ElementoCatalogo.class, id);
    }

    public void delete(ElementoCatalogo e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }
    //-----------------metodi richiesti dalla traccia dell'esercizio

    //1- aggiunta elemento
    public void save(ElementoCatalogo e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    //2- rimozione elemento dato un codice isbn
    public void deleteByIsbn(String isbn) {
        Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.isbn = :isbn");
        q.setParameter("isbn", isbn);
        ElementoCatalogo e = (ElementoCatalogo) q.getSingleResult();
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    //3- ricerca per isbn
    public ElementoCatalogo findByIsbn(String isbn) {
        Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.isbn = :isbn");
        q.setParameter("isbn", isbn);
        return (ElementoCatalogo) q.getSingleResult();
    }

    //4- ricerca per anno di pubblicazione
    public List<ElementoCatalogo> findByYear(int anno) {
        Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno");
        q.setParameter("anno", anno);
        return q.getResultList();
    }

    //5- ricerca per autore
    public List<ElementoCatalogo> findByAuthor(String nomeAutore) {
        Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.autore = :nomeAutore AND TYPE(e) = Libro");
        q.setParameter("nomeAutore", nomeAutore);
        return q.getResultList();
    }

    //6- ricerca per titolo anche parziale
    public List<ElementoCatalogo> findByTitle(String titolo) {
        Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo");
        q.setParameter("titolo", "%" + titolo + "%");
        return q.getResultList();
    }

    /*public List<ElementoCatalogo> findElementiInPrestitoDaTesseraUtente(long numeroTesseraUtente) {

    }*/

    public List<ElementoCatalogo> findPrestitiScadutiENonRestituiti() {
        LocalDate today = LocalDate.now();
        Query q = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < :today");
        q.setParameter("today", today);
        return q.getResultList();
    }
}

