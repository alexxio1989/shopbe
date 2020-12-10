package com.ws.models;

import java.util.ArrayList;
import java.util.List;

public class Dominio {

    private int id;
	private String codice;
    private String descrizione;
    private List<SubDominio> sottoTipi = new ArrayList<SubDominio>();

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

    public List<SubDominio> getSottoTipi() {
        return sottoTipi;
    }

    public void setSottoTipi(List<SubDominio> sottoTipi) {
        this.sottoTipi = sottoTipi;
    }
}
