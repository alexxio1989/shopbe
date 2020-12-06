package com.ws.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.Prodotto;
import com.ws.repository.IProdottoRepo;
import com.ws.response.ProdottoResponse;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController implements IController<Prodotto,ProdottoResponse> {

    @Autowired
    private IProdottoRepo repo;


    @Override
    public ResponseEntity<ProdottoResponse> delete(@RequestBody Prodotto obj) throws DataAccessException, SQLException  {
    	ProdottoResponse res = repo.delete(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<ProdottoResponse> get(@RequestBody Prodotto obj) throws DataAccessException, SQLException  {
    	ProdottoResponse res = repo.get(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<ProdottoResponse> save(@RequestBody Prodotto obj) throws DataAccessException, SQLException  {
    	ProdottoResponse res = repo.save(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<ProdottoResponse> update(@RequestBody Prodotto obj) throws DataAccessException, SQLException  {
    	ProdottoResponse res = repo.update(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

	@Override
	public ResponseEntity<ProdottoResponse> getAll() throws DataAccessException, SQLException {
		ProdottoResponse res = repo.getAll();
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

    
}
