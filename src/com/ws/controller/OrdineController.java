package com.ws.controller;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.Ordine;
import com.ws.repository.IOrdineRepo;
import com.ws.response.OrdineResponse;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/ordine")
public class OrdineController implements IController<Ordine, OrdineResponse> {

    @Autowired
    private IOrdineRepo repo;

    @Override
    public ResponseEntity<OrdineResponse> delete(@RequestBody Ordine obj) throws DataAccessException, SQLException {
    	OrdineResponse res = repo.delete(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<OrdineResponse> get(@RequestBody Ordine obj) throws DataAccessException, SQLException  {
    	OrdineResponse res = repo.get(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<OrdineResponse> save(@RequestBody Ordine obj) throws DataAccessException, SQLException  {
    	OrdineResponse res = repo.save(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<OrdineResponse> update(@RequestBody Ordine obj) throws DataAccessException, SQLException  {
    	OrdineResponse res = repo.update(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

	@Override
	public ResponseEntity<OrdineResponse> getAll() throws DataAccessException, SQLException {
		OrdineResponse res = repo.getAll();
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

    
}