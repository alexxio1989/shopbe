package com.ws.repository;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;

import com.ws.models.Utente;
import com.ws.response.UtenteResponse;

public interface IUtenteRepo<I,O> {

    public ResponseEntity<O> save(I obj) throws DataAccessException, SQLException  ;
     
    public ResponseEntity<O> update(I obj) throws DataAccessException, SQLException ;
 
    public ResponseEntity<O> get(I obj) throws DataAccessException, SQLException ;
 
    public ResponseEntity<O> delete(I obj) throws DataAccessException, SQLException ;

    public ResponseEntity<UtenteResponse> login(Utente obj) throws DataAccessException, SQLException ;

}
