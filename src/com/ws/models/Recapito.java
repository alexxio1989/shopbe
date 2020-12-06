package com.ws.models;

public class Recapito {

    private int id;
    private String indirizzo;
    private String zip;
    private String citta;
    private String telefono;
    private int idSoggetto;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getIdSoggetto() {
		return idSoggetto;
	}
	public void setIdSoggetto(int idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
    
}
