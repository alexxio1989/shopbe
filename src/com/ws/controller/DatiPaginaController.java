package com.ws.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ws.enums.StatusAcquisto.EnumStatusAcquisto;
import com.ws.models.Negozio;
import com.ws.repository.IAcquistoRepo;
import com.ws.repository.INegozioRepo;
import com.ws.repository.ITipoRepo;
import com.ws.response.DatiPaginaResponse;
import com.ws.response.NegozioResponse;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/datipagina")
public class DatiPaginaController {
	
	@Autowired
    private INegozioRepo repo;
	
	@Autowired
    private ITipoRepo repoTipo;
	
	@Autowired
	private IAcquistoRepo repoAcquisto;
	
	@CrossOrigin("*")
    @RequestMapping(value = "/home" ,  produces = "application/json",  method = RequestMethod.GET)
    ResponseEntity<DatiPaginaResponse> home() throws DataAccessException, SQLException{
		NegozioResponse all = repo.getAll();
		DatiPaginaResponse datiPaginaResponse = new DatiPaginaResponse(all.getHttpStatus() , all.getStatus());
		List<Negozio> res = all.getList();
		datiPaginaResponse.setList(res);
		return Utils.getResponseEntity(datiPaginaResponse, all.getHttpStatus());
		
	}
	
	@CrossOrigin("*")
    @RequestMapping(value = "/admin" ,  produces = "application/json",  method = RequestMethod.GET)
    ResponseEntity<DatiPaginaResponse> admin() throws DataAccessException, SQLException{
		NegozioResponse all = repo.getAll();
		DatiPaginaResponse datiPaginaResponse = new DatiPaginaResponse(all.getHttpStatus() , all.getStatus());
		List<Negozio> res = all.getList();
		datiPaginaResponse.setList(res);
		datiPaginaResponse.setListTipo(repoTipo.getAll().getList());
		datiPaginaResponse.setListAcquisti(repoAcquisto.getAll().getList());
		datiPaginaResponse.setListStatus(EnumStatusAcquisto.getAll());
		return Utils.getResponseEntity(datiPaginaResponse, all.getHttpStatus());
		
	}

}
