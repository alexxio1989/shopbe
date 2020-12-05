package com.ws.models;

import java.util.List;

public class Ordine {
    
    private int id;
    private List<Prodotto> prodotti;
    private String aquistato;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public String getAquistato() {
        return aquistato;
    }

    public void setAquistato(String aquistato) {
        this.aquistato = aquistato;
    }
}
