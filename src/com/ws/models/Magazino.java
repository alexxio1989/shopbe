package com.ws.models;

import java.util.List;

public class Magazino {

    private int id;
    private Prodotto prodottoSelected = new Prodotto();
    private List<Prodotto> prodotti;
    private int idNegozio;

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

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public int getIdNegozio() {
        return idNegozio;
    }

    public void setIdNegozio(int idNegozio) {
        this.idNegozio = idNegozio;
    }
    
}
