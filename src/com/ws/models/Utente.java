package com.ws.models;

import java.util.List;

public class Utente {

    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Dominio tipoUtente;
    private Recapito recapito = new Recapito();
    private List<Ordine> ordini;
    private List<Acquisto> acquisti;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dominio getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(Dominio tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

    public Recapito getRecapito() {
        return recapito;
    }

    public void setRecapito(Recapito recapito) {
        this.recapito = recapito;
    }

    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    public List<Acquisto> getAcquisti() {
        return acquisti;
    }

    public void setAcquisti(List<Acquisto> acquisti) {
        this.acquisti = acquisti;
    }
    
}
