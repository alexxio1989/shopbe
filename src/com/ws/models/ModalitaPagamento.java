package com.ws.models;

public class ModalitaPagamento {
	
	int id;
	String codice;
	String descrizione;
	boolean enable;
	
	int idNegozio;
	
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
	public int getIdNegozio() {
		return idNegozio;
	}
	public void setIdNegozio(int idNegozio) {
		this.idNegozio = idNegozio;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	
}
