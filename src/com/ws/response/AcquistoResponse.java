package com.ws.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ws.models.Acquisto;
import com.ws.models.Negozio;
import com.ws.models.Utente;
import com.ws.response.core.ResponseCore;

public class AcquistoResponse extends ResponseCore {
	
	public AcquistoResponse(HttpStatus httpStatus , String status) {
		super.setHttpStatus(httpStatus);
		super.setStatus(status);
	}

    private List<Acquisto> list;

	public List<Acquisto> getList() {
		return list;
	}

	public void setList(List<Acquisto> list) {
		this.list = list;
	}
    
    
    
}
