package com.ws.repository_impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.models.Recapito;
import com.ws.models.Utente;
import com.ws.repository.IRecapitoRepo;
import com.ws.repository.IUtenteRepo;
import com.ws.repository.repoenums.Metodi.EnumMetodi;
import com.ws.response.UtenteResponse;
import com.ws.response.enumresponse.ResponseStatus.EnumResponseStatus;
import com.ws.rowmapper.UtenteRowMapper;
import com.ws.utils.JdbcUtil;

@Repository
public class UtenteRempoImpl implements IUtenteRepo {

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private UtenteRowMapper rm;

    @Value("${utente.save}")
    protected String querySave;

    @Value("${utente.delete}")
    protected String queryDelete;

    @Value("${utente.update}")
    protected String queryUpdate;

    @Value("${utente.get}")
    protected String queryGet;

    @Value("${utente.login}")
    protected String queryLogin;
    
    @Value("${count.utente.by.email}")
    protected String queryCountUtenteByEmail;
    
    @Value("${get.utente.by.email}")
    protected String queryGetUtenteByEmail;

    @Autowired
    private IRecapitoRepo recapitoRepo;


    @Override
    public UtenteResponse save(Utente obj) {
    	UtenteResponse res = null;
    	if(getUtenteByEmail(obj.getEmail()) == 0) {
    		res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.SAVE));
    		Recapito recapito = recapitoRepo.save(obj.getRecapito());
    		try {
				jdbcUtil.update(querySave, obj.getNome() ,obj.getCognome(),obj.getEmail() , obj.getPassword() , 3 , recapito.getId());
				res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.SAVE));
			} catch (DataAccessException | SQLException e) {
				res = new UtenteResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.SAVE));
				e.printStackTrace();
			}
    		
    	} else {
    		res = new UtenteResponse(HttpStatus.BAD_REQUEST, "EMAIL NON VALIDA");
    	}
        return res;
    }

    @Override
    public UtenteResponse update(Utente obj) {
    	 UtenteResponse res = null;
        try {
			jdbcUtil.update(queryUpdate,new Object[] { obj.getId() });
			res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.UPDATE));
		} catch (DataAccessException | SQLException e) {
			res = new UtenteResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.UPDATE_ERROR));
			e.printStackTrace();
		}
        return get(obj,res);
    }

    @Override
    public UtenteResponse get(Utente obj) {
        UtenteResponse res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        return get(obj, res);
    }

	private UtenteResponse get(Utente obj, UtenteResponse res) {
		Utente utente;
		try {
			utente = jdbcUtil.queryForObj(queryLogin, new Object[] {obj.getId()}, rm);
			res.setUtente(utente);
		} catch (DataAccessException | SQLException e) {
			res = new UtenteResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.GET));
			e.printStackTrace();
		}
        return res;
	}

    @Override
    public UtenteResponse delete(Utente obj) {
    	UtenteResponse res = null;
        try {
			jdbcUtil.update(queryDelete,new Object[] { obj.getId() });
			res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.DELETE));
		} catch (DataAccessException | SQLException e) {
			res = new UtenteResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.DELETE_ERROR));
			e.printStackTrace();
		}
        return res;
    }

    @Override
    public UtenteResponse login(Utente obj) {
    	UtenteResponse res = null;
        Utente utente;
		try {
			utente = getUserByEmail(obj);
			res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.LOGIN));
			res.setUtente(utente);
		} catch (SQLException e) {
			res = new UtenteResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.LOGIN_ERROR));
			e.printStackTrace();
		}
        return res;
    }

	private Utente getUserByEmail(Utente obj) throws SQLException {
		Utente utente = jdbcUtil.queryForObj(queryLogin, new Object[] {obj.getEmail(),obj.getPassword()}, rm);
		return utente;
	}

	@Override
	public UtenteResponse getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer getUtenteByEmail(String email) {
		try {
			return jdbcUtil.queryForInteger(queryCountUtenteByEmail, new Object[]{email});
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Utente get(int id) {
		try {
			return jdbcUtil.queryForObj(queryGet, new Object[] {id}, rm);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public Utente getUserByEmail(String email) throws SQLException {
		return jdbcUtil.queryForObj(queryGetUtenteByEmail, new Object[]{email}, rm);
	}
    
}
