package com.ws.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.Acquisto;
import com.ws.repository.IAcquistoRepo;
import com.ws.response.AcquistoResponse;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/acquisto")
public class AcquistoController implements IController<Acquisto, AcquistoResponse> {

    @Autowired
    private IAcquistoRepo repo;

	@Override
	public ResponseEntity<AcquistoResponse> save(@RequestBody Acquisto obj) throws DataAccessException, SQLException {
		AcquistoResponse res = repo.save(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

	@Override
	public ResponseEntity<AcquistoResponse> update(@RequestBody Acquisto obj) throws DataAccessException, SQLException {
		AcquistoResponse res = repo.update(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

	@Override
	public ResponseEntity<AcquistoResponse> get(@RequestBody Acquisto obj) throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AcquistoResponse> delete(@RequestBody Acquisto obj) throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AcquistoResponse> getAll() throws DataAccessException, SQLException {
		AcquistoResponse res = repo.getAll();
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}
	
	@CrossOrigin("*")
    @RequestMapping(value = "/getallutente" ,  produces = "application/json",  method = RequestMethod.POST)
	public ResponseEntity<List<Acquisto>> getAllUtente(@RequestBody int idUtente) throws DataAccessException, SQLException {
		List<Acquisto> res = repo.getAllUtente(idUtente);
		return Utils.getResponseEntity(res,HttpStatus.OK);
	}

}
