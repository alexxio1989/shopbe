package com.ws.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ws.models.GiornoLavorativo;
import com.ws.response.core.ResponseCore;

public class GiornoLavorativoResponse extends ResponseCore {
	
	public GiornoLavorativoResponse(HttpStatus httpStatus , String status) {
		super.setHttpStatus(httpStatus);
		super.setStatus(status);
	}
	
	private List<GiornoLavorativo> giorniLavorativi;

	public List<GiornoLavorativo> getGiorniLavorativi() {
		return giorniLavorativi;
	}

	public void setGiorniLavorativi(List<GiornoLavorativo> giorniLavorativi) {
		this.giorniLavorativi = giorniLavorativi;
	}
	
	

}
