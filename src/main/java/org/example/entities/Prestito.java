package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente; // ogni prestito ha un solo utente


    @ManyToMany
    @JoinTable(
            name = "prestiti_elementicatalogo",
            joinColumns = @JoinColumn(name ="prestito_id"),
            inverseJoinColumns = @JoinColumn(name = "elementocatalogo_id")
    )
    private List<ElementoCatalogo> elementiPrestati = new ArrayList<>(); //relazione

    @Column(nullable = false)
    private LocalDate dataInizioPrestito;
    private LocalDate dataRestituzionePrevista;
    private LocalDate dataRestituzioneEffettiva;

    public Prestito(Utente utente, List<ElementoCatalogo> elementiPrestati, LocalDate dataInizioPrestito) {
        this.utente = utente;
        this.elementiPrestati = elementiPrestati;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
        /*non ho inserito la dataRestituzioneEffettiva perchè a logica quando creo un Prestito non conosco
        quando verrà effettivamente restituito il libro/rivista,
        ma posso settarlo successivamente quando accade l'evento.*/
    }

    public Prestito() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<ElementoCatalogo> getElementiPrestati() {
        return elementiPrestati;
    }

    public void setElementiPrestati(List<ElementoCatalogo> elementiPrestati) {
        this.elementiPrestati = elementiPrestati;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                /*", utente=" + utente +*/
                ", elementiPrestati=" + elementiPrestati +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva +
                '}';
    }
}
