package com.ws.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.Negozio;
import com.ws.repository.INegozioRepo;
import com.ws.response.NegozioResponse;
import com.ws.utils.Utils;


@RestController
@RequestMapping("/negozio")
public class NegozioController implements IController<Negozio, NegozioResponse> {

    @Autowired
    private INegozioRepo repo;

    @Override
    public ResponseEntity<NegozioResponse> delete(@RequestBody Negozio obj) throws DataAccessException, SQLException {
        NegozioResponse res = repo.delete(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<NegozioResponse> get(@RequestBody Negozio obj) throws DataAccessException, SQLException {
    	NegozioResponse res = repo.get(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<NegozioResponse> save(@RequestBody Negozio obj) throws DataAccessException, SQLException {
    	NegozioResponse res = repo.save(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<NegozioResponse> update(@RequestBody Negozio obj) throws DataAccessException, SQLException {
    	NegozioResponse res = repo.update(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

	@Override
	public ResponseEntity<NegozioResponse> getAll() throws DataAccessException, SQLException {
		NegozioResponse res = repo.getAll();
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

    
}
