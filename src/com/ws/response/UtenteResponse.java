package com.ws.response;

import org.springframework.http.HttpStatus;

import com.ws.models.Utente;
import com.ws.response.core.ResponseCore;

public class UtenteResponse extends ResponseCore {
	
	public UtenteResponse(HttpStatus httpStatus, String status) {
		super.setHttpStatus(httpStatus);
		super.setStatus(status);
	}

    private Utente utente;

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    
}
