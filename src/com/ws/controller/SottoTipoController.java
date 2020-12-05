package com.ws.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.Dominio;
import com.ws.models.SubDominio;
import com.ws.repository.ISottoTipoRepo;
import com.ws.repository.ITipoRepo;
import com.ws.response.TipoResponse;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/sottotipo")
public class SottoTipoController implements IController<SubDominio,SubDominio>   {
	
	@Autowired
    private ISottoTipoRepo<SubDominio,SubDominio> repo;

	@Override
	public ResponseEntity<SubDominio> save(@RequestBody SubDominio obj) throws DataAccessException, SQLException {
		return Utils.getResponseEntity(repo.save(obj), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubDominio> update(@RequestBody SubDominio obj) throws DataAccessException, SQLException {
		return Utils.getResponseEntity(repo.update(obj), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubDominio> get(@RequestBody SubDominio obj) throws DataAccessException, SQLException {
		return Utils.getResponseEntity(repo.get(obj), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubDominio> delete(@RequestBody SubDominio obj) throws DataAccessException, SQLException {
		return Utils.getResponseEntity(repo.delete(obj), HttpStatus.OK);
	}

}
