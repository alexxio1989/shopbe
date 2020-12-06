package com.ws.response;

import org.springframework.http.HttpStatus;

import com.ws.models.Negozio;
import com.ws.models.Utente;
import com.ws.response.core.ResponseCore;

public class AcquistoResponse extends ResponseCore {
	
	public AcquistoResponse(HttpStatus httpStatus , String status) {
		super.setHttpStatus(httpStatus);
		super.setStatus(status);
	}

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
