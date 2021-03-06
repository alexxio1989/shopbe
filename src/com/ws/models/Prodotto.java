package com.ws.models;

import java.math.BigDecimal;

public class Prodotto {

    private int id;
    private String nomeProdotto;
    private String descrizione;
    private BigDecimal prezzo;
    private SubDominio tipo = new SubDominio();
    private String image;
    private BigDecimal qntRimanente;
    private String unita;
    private BigDecimal qnt;
    private BigDecimal step;
    private int idNegozio;

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

    public BigDecimal getQntRimanente() {
        return qntRimanente;
    }

    public void setQntRimanente(BigDecimal qntRimanente) {
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

	public BigDecimal getQnt() {
		return qnt;
	}

	public void setQnt(BigDecimal qnt) {
		this.qnt = qnt;
	}

	public BigDecimal getStep() {
		return step;
	}

	public void setStep(BigDecimal step) {
		this.step = step;
	}

	public int getIdNegozio() {
		return idNegozio;
	}

	public void setIdNegozio(int idNegozio) {
		this.idNegozio = idNegozio;
	}
	
	

    
}
