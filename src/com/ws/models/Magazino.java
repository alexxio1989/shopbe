package com.ws.models;

import java.util.List;

public class Magazino {

    private int id;
    private Prodotto prodottoSelected = new Prodotto();
    private int idNegozio;
    private List<Dominio> tipiAssociati;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prodotto getProdottoSelected() {
        return prodottoSelected;
    }

    public void setProdottoSelected(Prodotto prodottoSelected) {
        this.prodottoSelected = prodottoSelected;
    }


    public int getIdNegozio() {
        return idNegozio;
    }

    public void setIdNegozio(int idNegozio) {
        this.idNegozio = idNegozio;
    }

	public List<Dominio> getTipiAssociati() {
		return tipiAssociati;
	}

	public void setTipiAssociati(List<Dominio> tipiAssociati) {
		this.tipiAssociati = tipiAssociati;
	}
    
    
    
}
