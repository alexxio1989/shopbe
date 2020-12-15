package com.ws.controller;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.GiornoLavorativo;
import com.ws.repository.IGiornoLavorativoRepo;
import com.ws.response.GiornoLavorativoResponse;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/ordine")
public class GiornoLavorativoController implements IController<GiornoLavorativo, GiornoLavorativoResponse> {

    @Autowired
    private IGiornoLavorativoRepo repo;

    @Override
    public ResponseEntity<GiornoLavorativoResponse> delete(@RequestBody GiornoLavorativo obj) throws DataAccessException, SQLException {
    	GiornoLavorativoResponse res = repo.delete(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<GiornoLavorativoResponse> get(@RequestBody GiornoLavorativo obj) throws DataAccessException, SQLException  {
    	GiornoLavorativoResponse res = repo.get(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<GiornoLavorativoResponse> save(@RequestBody GiornoLavorativo obj) throws DataAccessException, SQLException  {
		return null;
    }
    
    @CrossOrigin("*")
    @RequestMapping(value = "/saveGiorni" ,  produces = "application/json",  method = RequestMethod.POST)
    public ResponseEntity<GiornoLavorativoResponse> save(@RequestBody List<GiornoLavorativo> obj) throws DataAccessException, SQLException  {
    	GiornoLavorativoResponse res = repo.save(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<GiornoLavorativoResponse> update(@RequestBody GiornoLavorativo obj) throws DataAccessException, SQLException  {
    	GiornoLavorativoResponse res = repo.update(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

	@Override
	public ResponseEntity<GiornoLavorativoResponse> getAll() throws DataAccessException, SQLException {
		GiornoLavorativoResponse res = repo.getAll();
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

    
}