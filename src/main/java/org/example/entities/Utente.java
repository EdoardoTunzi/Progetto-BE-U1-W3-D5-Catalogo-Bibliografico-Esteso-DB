package org.example.entities;

import java.time.LocalDate;

public class Utente {
    private long id;
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private long numeroDiTessera; //unique

    public Utente() {
    }

    public Utente(String nome, String cognome, LocalDate dataDiNascita, long numeroDiTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.numeroDiTessera = numeroDiTessera;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public long getNumeroDiTessera() {
        return numeroDiTessera;
    }

    public void setNumeroDiTessera(long numeroDiTessera) {
        this.numeroDiTessera = numeroDiTessera;
    }

    @Override
    public String toString() {
        return "Utente(" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", numeroDiTessera=" + numeroDiTessera +
                ')';
    }
}
