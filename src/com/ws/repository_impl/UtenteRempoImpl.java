package com.ws.repository_impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ws.models.Recapito;
import com.ws.models.Utente;
import com.ws.repository.IRecapitoRepo;
import com.ws.repository.IUtenteRepo;
import com.ws.response.UtenteResponse;
import com.ws.rowmapper.UtenteRowMapper;
import com.ws.utils.JdbcUtil;
import com.ws.utils.Utils;

@Repository
public class UtenteRempoImpl implements IUtenteRepo<Utente,UtenteResponse> {

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
    private IRecapitoRepo<Recapito,Recapito> recapitoRepo;


    @Override
    public ResponseEntity<UtenteResponse> save(Utente obj) throws DataAccessException, SQLException {
        UtenteResponse res = new UtenteResponse();
        Recapito recapito = recapitoRepo.save(obj.getRecapito());
        jdbcUtil.update(querySave, obj.getNome() ,obj.getCognome(),obj.getEmail() , obj.getPassword() , 2 , recapito.getId());
        return Utils.getResponseEntity(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtenteResponse> update(Utente obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryUpdate,new Object[] { obj.getId() });
        return get(obj);
    }

    @Override
    public ResponseEntity<UtenteResponse> get(Utente obj) throws DataAccessException, SQLException {
        UtenteResponse res = new UtenteResponse();
        Utente utente = jdbcUtil.queryForObj(queryLogin, new Object[] {obj.getId()}, rm);
        res.setUtente(utente);
        return Utils.getResponseEntity(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtenteResponse> delete(Utente obj) throws DataAccessException, SQLException {
        UtenteResponse res = new UtenteResponse();
        jdbcUtil.update(queryDelete,new Object[] { obj.getId() });
        return Utils.getResponseEntity(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtenteResponse> login(Utente obj) throws DataAccessException, SQLException {
        UtenteResponse res = new UtenteResponse();
        Utente utente = jdbcUtil.queryForObj(queryLogin, new Object[] {obj.getEmail(),obj.getPassword()}, rm);
        res.setUtente(utente);
        return Utils.getResponseEntity(res, HttpStatus.OK);
    }
    
}
