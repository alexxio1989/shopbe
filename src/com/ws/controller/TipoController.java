package com.ws.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.Dominio;
import com.ws.repository.ITipoRepo;
import com.ws.response.TipoResponse;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/tipo")
public class TipoController implements IController<Dominio,TipoResponse>  {
	
	@Autowired
    private ITipoRepo repo;

	@Override
	public ResponseEntity<TipoResponse> save(@RequestBody Dominio obj) throws DataAccessException, SQLException {
		TipoResponse res = repo.save(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

	@Override
	public ResponseEntity<TipoResponse> update(@RequestBody Dominio obj) throws DataAccessException, SQLException {
		TipoResponse res = repo.update(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

	@Override
	public ResponseEntity<TipoResponse> get(@RequestBody Dominio obj) throws DataAccessException, SQLException {
		TipoResponse res = repo.get(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

	@Override
	public ResponseEntity<TipoResponse> delete(@RequestBody Dominio obj) throws DataAccessException, SQLException {
		TipoResponse res = repo.delete(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

	@Override
	public ResponseEntity<TipoResponse> getAll() throws DataAccessException, SQLException {
		TipoResponse res = repo.getAll();
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

}
