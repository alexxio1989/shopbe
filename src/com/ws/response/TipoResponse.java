package com.ws.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ws.models.Dominio;
import com.ws.response.core.ResponseCore;

public class TipoResponse extends ResponseCore {
	
	public TipoResponse(HttpStatus httpStatus, String status) {
		super.setHttpStatus(httpStatus);
		super.setStatus(status);
	}

    private List<Dominio> list;

    public List<Dominio> getList() {
        return list;
    }

    public void setList(List<Dominio> list) {
        this.list = list;
    }
    
}
