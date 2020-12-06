package com.ws.models;

public class Negozio {

    private int id;
    private String nome;
    private Magazino magazino = new Magazino();
    private Recapito recapito = new Recapito();

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

    public Magazino getMagazino() {
        return magazino;
    }

    public void setMagazino(Magazino magazino) {
        this.magazino = magazino;
    }

    public Recapito getRecapito() {
        return recapito;
    }

    public void setRecapito(Recapito recapito) {
        this.recapito = recapito;
    }
    
}
