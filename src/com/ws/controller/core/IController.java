package com.ws.controller.core;

import java.sql.SQLException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public interface IController<I,O> {

    @CrossOrigin("*")
    @RequestMapping(value = "/save" ,  produces = "application/json",  method = RequestMethod.POST)
    ResponseEntity<O> save(I obj) throws DataAccessException, SQLException ;
    
    @CrossOrigin("*")
    @RequestMapping(value = "/update" ,  produces = "application/json",  method = RequestMethod.POST)
    ResponseEntity<O> update(I obj) throws DataAccessException, SQLException ;

    @CrossOrigin("*")
    @RequestMapping(value = "/get" ,  produces = "application/json",  method = RequestMethod.POST)
    ResponseEntity<O> get(I obj) throws DataAccessException, SQLException ;

    @CrossOrigin("*")
    @RequestMapping(value = "/delete" ,  produces = "application/json",  method = RequestMethod.POST)
    ResponseEntity<O> delete(I obj) throws DataAccessException, SQLException ;
    
}
