package com.ws.response;

import com.ws.models.Negozio;
import com.ws.models.Utente;
import com.ws.response.core.ResponseCore;

public class OrdineResponse extends ResponseCore {

    private Negozio negozio;
    private Utente utente;

    public Negozio getNegozio() {
        return negozio;
    }

    public void setNegozio(Negozio negozio) {
        this.negozio = negozio;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    
}
