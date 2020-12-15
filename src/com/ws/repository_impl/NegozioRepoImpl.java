package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.models.GiornoLavorativo;
import com.ws.models.Negozio;
import com.ws.models.Recapito;
import com.ws.repository.INegozioRepo;
import com.ws.repository.repoenums.Metodi.EnumMetodi;
import com.ws.response.NegozioResponse;
import com.ws.response.enumresponse.ResponseStatus.EnumResponseStatus;
import com.ws.rowmapper.NegozioRowMapper;
import com.ws.utils.JdbcUtil;


@Repository
public class NegozioRepoImpl implements INegozioRepo {

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private NegozioRowMapper rm;

    @Autowired
    private RecapitoRepoImpl recapitoRepo;
    
    @Autowired
    private GiornoLavorativoRepoImpl giorniRepo;

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
    public NegozioResponse save(Negozio obj) throws DataAccessException, SQLException {
        Recapito newRecapito = null;
       
        newRecapito = recapitoRepo.save(obj.getRecapito());
        
        if(newRecapito != null){
            int id = jdbcUtil.saveAndGetId( new Object[] { obj.getNome(), newRecapito.getId() } , querySave);
            obj.getGiorniLavorativi().stream().forEach(g ->  g.setIdNegozio(id));
            giorniRepo.save(obj.getGiorniLavorativi());
        }

        return getAll();
    }

    @Override
    public NegozioResponse update(Negozio obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryUpdate, new Object[] { obj.getNome() , obj.getId()});
        obj.getGiorniLavorativi().stream().forEach(g ->  g.setIdNegozio(obj.getId()));
        giorniRepo.update(obj.getGiorniLavorativi());

        return getAll();
    }

    @Override
    public NegozioResponse get(Negozio obj) throws DataAccessException, SQLException {
        return null;
    }

    @Override
    public NegozioResponse delete(Negozio obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryDelete, new Object[] { obj.getId()});
		giorniRepo.deleteAll(obj.getId() );
        return getAll();
    }

	@Override
	public NegozioResponse getAll() throws DataAccessException, SQLException {
		NegozioResponse negozioResponse = new NegozioResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        List<Negozio> list = jdbcUtil.query(queryGetAll, rm);
        negozioResponse.setList(list);
        for (Negozio negozio : list) {
        	negozio.setGiorniLavorativi(giorniRepo.getAll(negozio.getId()).getGiorniLavorativi());
		}
        return negozioResponse;
	}
    
}
