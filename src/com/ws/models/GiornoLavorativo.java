package com.ws.models;

public class GiornoLavorativo {
	
	int id;
	int IdNegozio;
	int day;
    String descrizione;
    String orarioApertura;
    String orarioPausaInizio;
    String orarioPausaFine;
    String orarioChiusura;
    
    boolean chiuso;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdNegozio() {
		return IdNegozio;
	}
	public void setIdNegozio(int idNegozio) {
		IdNegozio = idNegozio;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getOrarioApertura() {
		return orarioApertura;
	}
	public void setOrarioApertura(String orarioApertura) {
		this.orarioApertura = orarioApertura;
	}
	public String getOrarioPausaInizio() {
		return orarioPausaInizio;
	}
	public void setOrarioPausaInizio(String orarioPausaInizio) {
		this.orarioPausaInizio = orarioPausaInizio;
	}
	public String getOrarioPausaFine() {
		return orarioPausaFine;
	}
	public void setOrarioPausaFine(String orarioPausaFine) {
		this.orarioPausaFine = orarioPausaFine;
	}
	public String getOrarioChiusura() {
		return orarioChiusura;
	}
	public void setOrarioChiusura(String orarioChiusura) {
		this.orarioChiusura = orarioChiusura;
	}
	public boolean isChiuso() {
		return chiuso;
	}
	public void setChiuso(boolean chiuso) {
		this.chiuso = chiuso;
	}

}
