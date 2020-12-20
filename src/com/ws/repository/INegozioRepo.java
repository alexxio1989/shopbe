package com.ws.repository;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.ws.models.Negozio;
import com.ws.repository.core.IRepository;
import com.ws.response.NegozioResponse;


public interface INegozioRepo extends IRepository<Negozio,NegozioResponse> {
	public Negozio get(int id) throws DataAccessException, SQLException ;
}
