package com.ws.models;

import java.util.List;

public class Negozio {

    private int id;
    private String nome;
    private Magazino magazino = new Magazino();
    private Recapito recapito = new Recapito();
    private List<GiornoLavorativo> giorniLavorativi; 

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

	public List<GiornoLavorativo> getGiorniLavorativi() {
		return giorniLavorativi;
	}

	public void setGiorniLavorativi(List<GiornoLavorativo> giorniLavorativi) {
		this.giorniLavorativi = giorniLavorativi;
	}
    
    
}
