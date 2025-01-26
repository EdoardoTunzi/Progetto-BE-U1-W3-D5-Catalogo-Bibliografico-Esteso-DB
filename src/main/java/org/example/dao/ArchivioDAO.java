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
    //Ciao Umberto, come mi sembra di aver capito nella call di venerdì, ho creato un unico DAO,
    // non so se ricordo bene, ad ogni modo sappi che non ho creato DAO separati solo per seguire la richiesta,
    // ma sono consapevole che bisognerebbe crearli.


    //metodi standard del dao

    public ElementoCatalogo getElemById(long id) {
        return em.find(ElementoCatalogo.class, id);
    }

    public Utente getUserById(long id) {
        return em.find(Utente.class, id);
    }

    public Prestito getPrestitoById(long id) {
        return em.find(Prestito.class, id);
    }


    public void delete(ElementoCatalogo e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }
    //----------------- metodi richiesti dalla traccia dell'esercizio --------------------

    //1- aggiunta elemento
    public void saveElemento(ElementoCatalogo e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public void saveUtente(Utente u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    public void savePrestito(Prestito p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }


    //2- rimozione elemento dato un codice isbn
    public void deleteElemByIsbn(String isbn) {
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
        System.out.println("Elemento con isbn " + isbn + " trovato:");
        return (ElementoCatalogo) q.getSingleResult();
    }

    //4- ricerca per anno di pubblicazione
    public List<ElementoCatalogo> findByYear(int anno) {
        Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno");
        q.setParameter("anno", anno);
        System.out.println("Elementi trovati con anno di pubblicazione " + anno + " :");
        return q.getResultList();
    }

    //5- ricerca per autore
    public List<ElementoCatalogo> findByAuthor(String nomeAutore) {
        Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.autore = :nomeAutore AND TYPE(e) = Libro");
        q.setParameter("nomeAutore", nomeAutore);
        System.out.println("Elementi trovati, da autore " + nomeAutore + " :");
        return q.getResultList();
    }

    //6- ricerca per titolo anche parziale
    public List<ElementoCatalogo> findByTitle(String titolo) {
        Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo");
        q.setParameter("titolo", "%" + titolo + "%");
        System.out.println("Elementi che nel titolo contengono la parola " + titolo + " :");
        return q.getResultList();
    }

    //7- ricerca elementi attualmente in prestito dato un numero di tessera utente
    public List<ElementoCatalogo> findElementiInPrestitoByTesseraUtente(long numeroTessera) {
        Query q = em.createQuery("SELECT e FROM Prestito p JOIN p.elementiPrestati e WHERE p.utente.numeroDiTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL");
        q.setParameter("numeroTessera", numeroTessera);
        System.out.println("Elementi attualmente in prestito collegati al numero di tessera: " + numeroTessera);
        return q.getResultList();
    }

    //8- ricerca prestiti scaduti e non restituiti
    public List<Prestito> findPrestitiScadutiENonRestituiti() {
        LocalDate today = LocalDate.now();
        Query q = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < :today");
        q.setParameter("today", today);
        System.out.println("Prestiti attualmente scaduti e che non sono stati restituiti: ");
        return q.getResultList();
    }
}

