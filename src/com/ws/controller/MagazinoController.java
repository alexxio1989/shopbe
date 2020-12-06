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
import com.ws.models.Magazino;
import com.ws.repository.IMagazinoRepo;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/magazino")
public class MagazinoController implements IController<Magazino, Magazino> {

    @Autowired
    private IMagazinoRepo repo;

    @Override
    public ResponseEntity<Magazino> delete(@RequestBody Magazino obj) throws DataAccessException, SQLException {
    	Magazino res = repo.delete(obj);
		return Utils.getResponseEntity(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Magazino> get(@RequestBody Magazino obj) throws DataAccessException, SQLException {
    	Magazino res = repo.get(obj);
		return Utils.getResponseEntity(res,  HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Magazino> save(@RequestBody Magazino obj) throws DataAccessException, SQLException {
    	Magazino res = repo.save(obj);
		return Utils.getResponseEntity(res,  HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Magazino> update(@RequestBody Magazino obj) throws DataAccessException, SQLException {
    	Magazino res = repo.update(obj);
		return Utils.getResponseEntity(res,  HttpStatus.OK);
    }

	@Override
	public ResponseEntity<Magazino> getAll() throws DataAccessException, SQLException {
		Magazino res = repo.getAll();
		return Utils.getResponseEntity(res,  HttpStatus.OK);
	}

    
}

