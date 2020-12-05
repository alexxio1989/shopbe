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

@RestController
@RequestMapping("/prodotto")
public class ProdottoController implements IController<Prodotto,ProdottoResponse> {

    @Autowired
    private IProdottoRepo<Prodotto,ProdottoResponse> repo;


    @Override
    public ResponseEntity<ProdottoResponse> delete(@RequestBody Prodotto obj) throws DataAccessException, SQLException  {
        return repo.delete(obj);
    }

    @Override
    public ResponseEntity<ProdottoResponse> get(@RequestBody Prodotto obj) throws DataAccessException, SQLException  {
        return repo.get(obj);
    }

    @Override
    public ResponseEntity<ProdottoResponse> save(@RequestBody Prodotto obj) throws DataAccessException, SQLException  {
        return repo.save(obj);
    }

    @Override
    public ResponseEntity<ProdottoResponse> update(@RequestBody Prodotto obj) throws DataAccessException, SQLException  {
        return repo.update(obj);
    }

    
}
