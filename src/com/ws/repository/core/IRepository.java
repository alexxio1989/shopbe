package com.ws.repository.core;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

public interface IRepository<I,O> {
	
	public O save(I obj) throws DataAccessException, SQLException  ;
    
    public O update(I obj) throws DataAccessException, SQLException ;
 
    public O get(I obj) throws DataAccessException, SQLException ;
    
    public O getAll() throws DataAccessException, SQLException ;
 
    public O delete(I obj) throws DataAccessException, SQLException ;

}
