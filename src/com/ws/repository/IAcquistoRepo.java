package com.ws.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ws.models.Acquisto;
import com.ws.repository.core.IRepository;
import com.ws.response.AcquistoResponse;


public interface IAcquistoRepo extends IRepository<Acquisto,AcquistoResponse> {
	
	public List<Acquisto> getAllUtente(int id) throws DataAccessException, SQLException ;
	
}