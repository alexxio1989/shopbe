package com.ws.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ws.models.GiornoLavorativo;
import com.ws.repository.core.IRepository;
import com.ws.response.GiornoLavorativoResponse;

public interface IGiornoLavorativoRepo extends IRepository<GiornoLavorativo, GiornoLavorativoResponse>{
	
	public GiornoLavorativoResponse save(List<GiornoLavorativo> obj) throws DataAccessException, SQLException;
	public GiornoLavorativoResponse update(List<GiornoLavorativo> obj) throws DataAccessException, SQLException;
	public GiornoLavorativoResponse deleteAll(int idNegozio) throws DataAccessException, SQLException;
	public GiornoLavorativoResponse getAll(int idNegozio) throws DataAccessException, SQLException;

}
