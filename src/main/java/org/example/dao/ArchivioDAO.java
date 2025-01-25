package org.example.dao;

import org.example.entities.ElementoCatalogo;
import org.example.entities.Prestito;
import org.example.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class ArchivioDAO {

    private EntityManager em;

    public ArchivioDAO(EntityManager em) {
        this.em = em;
    }
    //metodi standard del dao

    public ElementoCatalogo getElemById(long id) {
        return em.find(ElementoCatalogo.class, id);
    }
    public Utente getUserById(long id) {
        return em.find(Utente.class, id);
    }
    public Prestito getPrestById(long id) {
        return em.find(Prestito.class, id);
    }


    public void delete(ElementoCatalogo e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }
    //-----------------metodi richiesti dalla traccia dell'esercizio

    //1- aggiunta elemento
    public void saveEle(ElementoCatalogo e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public void saveUtente(Utente u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    public void savePrest(Prestito p) {
        em.getTransaction().begin();
        em.persist(p);
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
        System.out.println("Elemento con isbn: " + isbn + " rimosso con successo!");
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
    //7- ricerca elementi attualmente in prestito dato un numero di tessera utente
    public List<ElementoCatalogo> findElementiInPrestitoByTesseraUtente(long numeroTessera) {
        Query q = em.createQuery("SELECT e FROM Prestito p JOIN p.elementiPrestati e WHERE p.utente.numeroDiTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL");
        q.setParameter("numeroTessera", numeroTessera);
        return q.getResultList();
    }

    //8- ricerca prestiti scaduti e non restituiti
    public List<Prestito> findPrestitiScadutiENonRestituiti() {
        LocalDate today = LocalDate.now();
        Query q = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < :today");
        q.setParameter("today", today);
        return q.getResultList();
    }
}

