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
import com.ws.models.SubDominio;
import com.ws.repository.ISottoTipoRepo;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/sottotipo")
public class SottoTipoController implements IController<SubDominio,SubDominio>   {
	
	@Autowired
    private ISottoTipoRepo repo;

	@Override
	public ResponseEntity<SubDominio> save(@RequestBody SubDominio obj) throws DataAccessException, SQLException {
		SubDominio res = repo.save(obj);
		return Utils.getResponseEntity(res, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubDominio> update(@RequestBody SubDominio obj) throws DataAccessException, SQLException {
		SubDominio res = repo.update(obj);
		return Utils.getResponseEntity(res, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubDominio> get(@RequestBody SubDominio obj) throws DataAccessException, SQLException {
		SubDominio res = repo.get(obj);
		return Utils.getResponseEntity(res, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubDominio> delete(@RequestBody SubDominio obj) throws DataAccessException, SQLException {
		SubDominio res = repo.delete(obj);
		return Utils.getResponseEntity(res, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubDominio> getAll() throws DataAccessException, SQLException {
		SubDominio res = repo.getAll();
		return Utils.getResponseEntity(res, HttpStatus.OK);
	}

}
