package com.ws.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ws.models.Prodotto;
import com.ws.response.core.ResponseCore;

public class ProdottoResponse extends ResponseCore {
	
	public ProdottoResponse(HttpStatus httpStatus, String status) {
		super.setHttpStatus(httpStatus);
		super.setStatus(status);
	}

    private List<Prodotto> list;
    private Prodotto prodotto;

    public List<Prodotto> getList() {
        return list;
    }

    public void setList(List<Prodotto> list) {
        this.list = list;
    }

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
    
    
    
}
