package com.ws.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ws.models.ModalitaPagamento;
import com.ws.response.core.ResponseCore;

public class ModPagamentoResponse extends ResponseCore {
	
	public ModPagamentoResponse(HttpStatus httpStatus , String status) {
		super.setHttpStatus(httpStatus);
		super.setStatus(status);
	}
	
	List<ModalitaPagamento> list;

	public List<ModalitaPagamento> getList() {
		return list;
	}

	public void setList(List<ModalitaPagamento> list) {
		this.list = list;
	}
	
	
	

}
