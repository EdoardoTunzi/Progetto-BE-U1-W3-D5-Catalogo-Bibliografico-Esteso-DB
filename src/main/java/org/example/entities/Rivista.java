package org.example.entities;

import org.example.enumerations.TipoPeriodicita;

public class Rivista extends ElementoCatalogo {
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
