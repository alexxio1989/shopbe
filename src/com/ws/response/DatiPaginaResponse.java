package com.ws.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ws.models.Acquisto;
import com.ws.models.Dominio;
import com.ws.models.Negozio;
import com.ws.response.core.ResponseCore;

public class DatiPaginaResponse extends ResponseCore{
		
		public DatiPaginaResponse(HttpStatus httpStatus, String status) {
			super.setHttpStatus(httpStatus);
			super.setStatus(status);
		}

	    private List<Negozio> list;
	    
	    private List<Dominio> listTipo;
	    
	    private List<Acquisto> listAcquisti;

	    private Negozio negozio;

	    public List<Negozio> getList() {
	        return list;
	    }

	    public void setList(List<Negozio> list) {
	        this.list = list;
	    }

	    public Negozio getNegozio() {
	        return negozio;
	    }

	    public void setNegozio(Negozio negozio) {
	        this.negozio = negozio;
	    }

		public List<Dominio> getListTipo() {
			return listTipo;
		}

		public void setListTipo(List<Dominio> listTipo) {
			this.listTipo = listTipo;
		}

		public List<Acquisto> getListAcquisti() {
			return listAcquisti;
		}

		public void setListAcquisti(List<Acquisto> listAcquisti) {
			this.listAcquisti = listAcquisti;
		}

}
