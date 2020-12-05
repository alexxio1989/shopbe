package com.ws.repository;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

public interface IMagazinoRepo<I,O>{

   public O save(I obj) throws DataAccessException, SQLException  ;
    
   public O update(I obj) throws DataAccessException, SQLException ;

   public O get(I obj) throws DataAccessException, SQLException ;

   public O delete(I obj) throws DataAccessException, SQLException ;
    
}
