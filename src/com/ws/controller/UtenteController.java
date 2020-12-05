package com.ws.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.Utente;
import com.ws.repository.IUtenteRepo;
import com.ws.response.UtenteResponse;

@RestController
@RequestMapping("/user")
public class UtenteController implements IController<Utente,UtenteResponse> {


    @Autowired
    private IUtenteRepo<Utente,UtenteResponse> repo;

    @Override
    public ResponseEntity<UtenteResponse> delete(Utente  obj) throws DataAccessException, SQLException  { 
        return repo.delete(obj);
    }

    @Override
    public ResponseEntity<UtenteResponse> get(Utente  obj) throws DataAccessException, SQLException  { 
        return repo.get(obj);
    }

    @Override
    public ResponseEntity<UtenteResponse> save(Utente  obj) throws DataAccessException, SQLException  { 
        return repo.save(obj);
    }

    @Override
    public ResponseEntity<UtenteResponse> update(Utente  obj) throws DataAccessException, SQLException  { 
        return repo.update(obj);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/login" ,  produces = "application/json",  method = RequestMethod.POST)
    public ResponseEntity<UtenteResponse> login(Utente  obj) throws DataAccessException, SQLException  { 
        return repo.login(obj);
    }
    
}
