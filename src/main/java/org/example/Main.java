package org.example;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo");
    private static EntityManager em = emf.createEntityManager();

    public static void main( String[] args ) {

        em.close();
        emf.close();

    }
}
