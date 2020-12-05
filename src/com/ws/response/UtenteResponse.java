package com.ws.response;

import com.ws.models.Utente;
import com.ws.response.core.ResponseCore;

public class UtenteResponse extends ResponseCore {

    private Utente utente;

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    
}
