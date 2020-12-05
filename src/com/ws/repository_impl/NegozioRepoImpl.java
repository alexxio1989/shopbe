package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ws.models.Negozio;
import com.ws.models.Recapito;
import com.ws.repository.INegozioRepo;
import com.ws.response.NegozioResponse;
import com.ws.rowmapper.NegozioRowMapper;
import com.ws.utils.JdbcUtil;
import com.ws.utils.Utils;


@Repository
public class NegozioRepoImpl implements INegozioRepo<Negozio,NegozioResponse>  {

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private NegozioRowMapper rm;

    @Autowired
    private RecapitoRepoImpl recapitoRepo;

    @Value("${negozio.save}")
    protected String querySave;

    @Value("${negozio.delete}")
    protected String queryDelete;

    @Value("${negozio.update}")
    protected String queryUpdate;

    @Value("${negozio.get}")
    protected String queryGet;

    @Value("${negozio.get.all}")
    protected String queryGetAll;

    @Override
    public ResponseEntity<NegozioResponse> save(Negozio obj) throws DataAccessException, SQLException {
        Recapito newRecapito = null;
       
        newRecapito = recapitoRepo.save(obj.getRecapito());
        
        if(newRecapito != null){
            jdbcUtil.update(querySave, new Object[] { obj.getNome(), newRecapito.getId() });
        }

        return get(obj);
    }

    @Override
    public ResponseEntity<NegozioResponse> update(Negozio obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryUpdate, new Object[] { obj.getNome() , obj.getId()});
        return get(obj);
    }

    @Override
    public ResponseEntity<NegozioResponse> get(Negozio obj) throws DataAccessException, SQLException {
        NegozioResponse negozioResponse = new NegozioResponse();
        List<Negozio> list = jdbcUtil.query(queryGetAll, rm);
        negozioResponse.setList(list);
        return Utils.getResponseEntity(negozioResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NegozioResponse> delete(Negozio obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryDelete, new Object[] { obj.getId()});
        return get(obj);
    }
    
}
