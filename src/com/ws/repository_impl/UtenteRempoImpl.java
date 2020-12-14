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

    @Autowired
    private IRecapitoRepo recapitoRepo;


    @Override
    public UtenteResponse save(Utente obj) throws DataAccessException, SQLException {
    	UtenteResponse res = null;
    	if(getUserByEmail(obj) == null) {
    		res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.SAVE));
    		Recapito recapito = recapitoRepo.save(obj.getRecapito());
    		jdbcUtil.update(querySave, obj.getNome() ,obj.getCognome(),obj.getEmail() , obj.getPassword() , 3 , recapito.getId());
    		
    	} else {
    		res = new UtenteResponse(HttpStatus.BAD_REQUEST, "EMAIL NON VALIDA");
    	}
        return res;
    }

    @Override
    public UtenteResponse update(Utente obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryUpdate,new Object[] { obj.getId() });
        return get(obj);
    }

    @Override
    public UtenteResponse get(Utente obj) throws DataAccessException, SQLException {
        UtenteResponse res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        Utente utente = jdbcUtil.queryForObj(queryLogin, new Object[] {obj.getId()}, rm);
        res.setUtente(utente);
        return res;
    }

    @Override
    public UtenteResponse delete(Utente obj) throws DataAccessException, SQLException {
        UtenteResponse res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.DELETE));
        jdbcUtil.update(queryDelete,new Object[] { obj.getId() });
        return res;
    }

    @Override
    public UtenteResponse login(Utente obj) throws DataAccessException, SQLException {
        UtenteResponse res = new UtenteResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.LOGIN));
        Utente utente = getUserByEmail(obj);
        res.setUtente(utente);
        return res;
    }

	private Utente getUserByEmail(Utente obj) throws SQLException {
		Utente utente = jdbcUtil.queryForObj(queryLogin, new Object[] {obj.getEmail(),obj.getPassword()}, rm);
		return utente;
	}

	@Override
	public UtenteResponse getAll() throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
    
}
