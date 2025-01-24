package org.example.entities;

import org.example.enumerations.TipoPeriodicita;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "riviste")
public class Rivista extends ElementoCatalogo {
    @Enumerated(EnumType.STRING)
    private TipoPeriodicita periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, TipoPeriodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Rivista() {
    }

    public TipoPeriodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(TipoPeriodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista, con " +
                "periodicità= " + periodicita +
                ';' + super.toString();
    }
}
