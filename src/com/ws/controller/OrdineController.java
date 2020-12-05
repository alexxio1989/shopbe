package com.ws.controller;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.Ordine;
import com.ws.repository.IOrdineRepo;
import com.ws.response.OrdineResponse;

@RestController
@RequestMapping("/ordine")
public class OrdineController implements IController<Ordine, OrdineResponse> {

    @Autowired
    private IOrdineRepo<Ordine,OrdineResponse> repo;

    @Override
    public ResponseEntity<OrdineResponse> delete(Ordine obj) throws DataAccessException, SQLException {
        return repo.delete(obj);
    }

    @Override
    public ResponseEntity<OrdineResponse> get(Ordine obj) throws DataAccessException, SQLException  {
        return repo.get(obj);
    }

    @Override
    public ResponseEntity<OrdineResponse> save(Ordine obj) throws DataAccessException, SQLException  {
        return repo.save(obj);
    }

    @Override
    public ResponseEntity<OrdineResponse> update(Ordine obj) throws DataAccessException, SQLException  {
        return repo.update(obj);
    }

    
}