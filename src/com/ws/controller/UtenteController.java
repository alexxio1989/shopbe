package com.ws.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.enums.Headers.EnumHeaders;
import com.ws.jwt.JwtTokenUtil;
import com.ws.models.Utente;
import com.ws.repository.IUtenteRepo;
import com.ws.response.UtenteResponse;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/user")
public class UtenteController implements IController<Utente,UtenteResponse> {


    @Autowired
    private IUtenteRepo repo;
    
    @Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Value("${jwt.header}")
    private String keyJwt;

    @Override
    public ResponseEntity<UtenteResponse> delete(@RequestBody Utente  obj) throws DataAccessException, SQLException  { 
    	UtenteResponse res = repo.delete(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<UtenteResponse> get(@RequestBody Utente  obj) throws DataAccessException, SQLException  { 
    	UtenteResponse res = repo.get(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<UtenteResponse> save(@RequestBody Utente  obj) throws DataAccessException, SQLException  { 
    	UtenteResponse res = repo.save(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @Override
    public ResponseEntity<UtenteResponse> update(@RequestBody Utente  obj) throws DataAccessException, SQLException  { 
    	UtenteResponse res = repo.update(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/login" ,  produces = "application/json",  method = RequestMethod.POST)
    public ResponseEntity<UtenteResponse> login(@RequestBody Utente  obj) throws DataAccessException, SQLException  { 
    	UtenteResponse res = repo.login(obj);
    	
    	if(HttpStatus.OK.value() == res.getHttpStatus().value()) {
	    	final String token = keyJwt + jwtTokenUtil.generateToken(res.getUtente());
	    	HttpHeaders headersResponse = new HttpHeaders();
	    	headersResponse.add(EnumHeaders.JWT_TOKEN.getCode(), token);
	    	headersResponse.add(EnumHeaders.TYPE_USER.getCode(), EnumHeaders.UTENTE.getCode());   
	    	headersResponse.add("Access-Control-Expose-Headers", EnumHeaders.TYPE_USER.getCode());
	    	headersResponse.add("Access-Control-Expose-Headers", EnumHeaders.JWT_TOKEN.getCode());
	        return new ResponseEntity<UtenteResponse>(res, headersResponse, HttpStatus.OK);
	    }
    	
		return Utils.getResponseEntity(res, res.getHttpStatus());
    }

	@Override
	public ResponseEntity<UtenteResponse> getAll() throws DataAccessException, SQLException {
		UtenteResponse res = repo.getAll();
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}
    
}
