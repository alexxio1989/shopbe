package com.ws.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.Negozio;
import com.ws.repository.INegozioRepo;
import com.ws.response.NegozioResponse;


@RestController
@RequestMapping("/negozio")
public class NegozioController implements IController<Negozio, NegozioResponse> {

    @Autowired
    private INegozioRepo<Negozio,NegozioResponse> repo;

    @Override
    public ResponseEntity<NegozioResponse> delete(Negozio obj) throws DataAccessException, SQLException {
        return repo.delete(obj);
    }

    @Override
    public ResponseEntity<NegozioResponse> get(Negozio obj) throws DataAccessException, SQLException {
        return repo.get(obj);
    }

    @Override
    public ResponseEntity<NegozioResponse> save(Negozio obj) throws DataAccessException, SQLException {
        return repo.save(obj);
    }

    @Override
    public ResponseEntity<NegozioResponse> update(Negozio obj) throws DataAccessException, SQLException {
        return repo.update(obj);
    }

    
}
