package com.ws.models;

import java.util.List;

public class SubDominio {

    private int id;
	private String codice;
    private String descrizione;
    private Dominio tipoPadre = new Dominio();
    private List<Prodotto> prodottiAssociati;

    public SubDominio(){

    }

    public SubDominio(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Dominio getTipoPadre() {
        return tipoPadre;
    }

    public void setTipoPadre(Dominio tipoPadre) {
        this.tipoPadre = tipoPadre;
    }

	public List<Prodotto> getProdottiAssociati() {
		return prodottiAssociati;
	}

	public void setProdottiAssociati(List<Prodotto> prodottiAssociati) {
		this.prodottiAssociati = prodottiAssociati;
	}

    
    
}
