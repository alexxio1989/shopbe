package com.ws.models;

import java.math.BigDecimal;

public class Prodotto {

    private int id;
    private String nomeProdotto;
    private String descrizione;
    private BigDecimal prezzo;
    private SubDominio tipo = new SubDominio();
    private String image;
    private int qntRimanente;
    private String unita;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public SubDominio getTipo() {
        return tipo;
    }

    public void setTipo(SubDominio tipo) {
        this.tipo = tipo;
    }

    public int getQntRimanente() {
        return qntRimanente;
    }

    public void setQntRimanente(int qntRimanente) {
        this.qntRimanente = qntRimanente;
    }

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUnita() {
		return unita;
	}

	public void setUnita(String unita) {
		this.unita = unita;
	}
	
	

    
}
