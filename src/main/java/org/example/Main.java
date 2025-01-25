package org.example;


import org.example.dao.ArchivioDAO;
import org.example.entities.*;
import org.example.enumerations.TipoPeriodicita;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        ArchivioDAO archivioDAO = new ArchivioDAO(em);
        //creazione oggetti Elementi e Utenti
       /* ElementoCatalogo libro1 = new Libro("1234567890123", "Il Signore degli Anelli", 1954, 1200, "J.R.R. Tolkien", "Fantasy");
        ElementoCatalogo libro2 = new Libro("9876543210987", "1984", 1949, 328, "George Orwell", "Distopia");
        ElementoCatalogo libro3 = new Libro("5678901234567", "To Kill a Mockingbird", 1960, 281, "Harper Lee", "Classico");
        ElementoCatalogo libro4 = new Libro("5432167890123", "Il Codice Da Vinci", 2003, 689, "Dan Brown", "Thriller");
        ElementoCatalogo libro5 = new Libro("1098765432109", "Harry Potter e la Pietra Filosofale", 1997, 223, "J.K. Rowling", "Fantasy");
        ElementoCatalogo rivista1 = new Rivista("1122334455667", "National Geographic", 2023, 35, TipoPeriodicita.MENSILE);
        ElementoCatalogo rivista2 = new Rivista("9988776655443", "Science", 2023, 45, TipoPeriodicita.SETTIMANALE);
        ElementoCatalogo rivista3 = new Rivista("7766554433221", "Time", 2023, 54, TipoPeriodicita.SETTIMANALE);
        ElementoCatalogo rivista4 = new Rivista("5544332211009", "Vogue", 2023, 35, TipoPeriodicita.MENSILE);
        ElementoCatalogo rivista5 = new Rivista("3322110099887", "Forbes", 2023, 60, TipoPeriodicita.SEMESTRALE);

        archivioDAO.saveEle(libro1);
        archivioDAO.saveEle(libro2);
        archivioDAO.saveEle(libro3);
        archivioDAO.saveEle(libro4);
        archivioDAO.saveEle(libro5);

        archivioDAO.saveEle(rivista1);
        archivioDAO.saveEle(rivista2);
        archivioDAO.saveEle(rivista3);
        archivioDAO.saveEle(rivista4);
        archivioDAO.saveEle(rivista5);

        Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 4, 12), 12345678);
        Utente utente2 = new Utente("Luigi", "Bianchi", LocalDate.of(1990, 3, 11), 87654321);
        Utente utente3 = new Utente("Anna", "Verdi", LocalDate.of(1980, 2, 12), 98765438);

        archivioDAO.saveUtente(utente1);
        archivioDAO.saveUtente(utente2);
        archivioDAO.saveUtente(utente3);
//-------------------- Prestiti
        List<ElementoCatalogo> prestitiUtente1 = new ArrayList<>();
        List<ElementoCatalogo> prestitiUtente2 = new ArrayList<>();
        List<ElementoCatalogo> prestiti1Utente3 = new ArrayList<>();
        List<ElementoCatalogo> prestiti2Utente3 = new ArrayList<>();

        prestitiUtente1.add(archivioDAO.getElemById(1));
        prestitiUtente1.add(archivioDAO.getElemById(2));

        prestitiUtente2.add(archivioDAO.getElemById(3));
        prestitiUtente2.add(archivioDAO.getElemById(4));

        prestiti1Utente3.add(archivioDAO.getElemById(5));
        prestiti1Utente3.add(archivioDAO.getElemById(6));

        prestiti2Utente3.add(archivioDAO.getElemById(7));
        prestiti2Utente3.add(archivioDAO.getElemById(8));



        Prestito prestito1 = new Prestito(archivioDAO.getUserById(1), prestitiUtente1, LocalDate.of(2025, 1, 2));
        Prestito prestito2 = new Prestito(archivioDAO.getUserById(2), prestitiUtente2, LocalDate.of(2025, 1, 2));
        Prestito prestito3 = new Prestito(archivioDAO.getUserById(3), prestiti1Utente3, LocalDate.of(2025, 1, 2));
        Prestito prestito4 = new Prestito(archivioDAO.getUserById(3), prestiti2Utente3, LocalDate.of(2023, 12, 8));

        archivioDAO.savePrest(prestito1);
        archivioDAO.savePrest(prestito2);
        archivioDAO.savePrest(prestito3);
        archivioDAO.savePrest(prestito4);
*/

        //--------------- esecuzione queries -------------

        //2- rimozione elemento dato un codice isbn - OK
        //archivioDAO.deleteByIsbn("3322110099887");

        //3- ricerca per isbn - OK
        /*ElementoCatalogo elementoCercato = archivioDAO.findByIsbn("9876543210987");
        System.out.println(elementoCercato);*/

        //4- ricerca per anno di pubblicazione - OK
        /*List<ElementoCatalogo> elperAnno = archivioDAO.findByYear(2003);
        elperAnno.forEach(System.out::println);*/

        //5- ricerca per autore -OK
       /* List<ElementoCatalogo> elPerAutore = archivioDAO.findByAuthor("George Orwell");
        elPerAutore.forEach(System.out::println);*/

        //6- ricerca per titolo anche parziale (Ricerco "etra" per Pietra Filosofale)
        /*List<ElementoCatalogo> eleByTitle = archivioDAO.findByTitle("etra");
        eleByTitle.forEach(System.out::println);*/

        //7- ricerca elementi attualmente in prestito dato un numero di tessera utente
        List<ElementoCatalogo> eleInPrestitoDaTessera = archivioDAO.findElementiInPrestitoByTesseraUtente(98765438);
        eleInPrestitoDaTessera.forEach(System.out::println);

        //8- ricerca prestiti scaduti e non restituiti - OK
        /*List<Prestito> prestitiScaduti = archivioDAO.findPrestitiScadutiENonRestituiti();
        prestitiScaduti.forEach(System.out::println);*/



        em.close();
        emf.close();

    }
}
