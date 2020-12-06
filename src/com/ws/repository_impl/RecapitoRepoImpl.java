package com.ws.repository_impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ws.models.Recapito;
import com.ws.repository.IRecapitoRepo;
import com.ws.rowmapper.RecapitoRowMapper;
import com.ws.utils.JdbcUtil;

@Repository
public class RecapitoRepoImpl implements IRecapitoRepo {

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private RecapitoRowMapper rm;

    @Value("${recapito.save}")
    protected String querySave;

    @Value("${recapito.delete}")
    protected String queryDelete;

    @Value("${recapito.update}")
    protected String queryUpdate;

    @Value("${recapito.get}")
    protected String queryGet;

    @Override
    public Recapito save(Recapito obj) throws DataAccessException, SQLException {
        int id = jdbcUtil.saveAndGetId(new Object[] {obj.getIndirizzo(),obj.getZip(),obj.getTelefono(),obj.getCitta()}, querySave);
        Recapito findRecapito = new Recapito();
        findRecapito.setId(id);
        return get(findRecapito);
    }

    @Override
    public Recapito update(Recapito obj) throws DataAccessException, SQLException {
        return null;
    }

    @Override
    public Recapito get(Recapito obj) throws DataAccessException, SQLException {
        return jdbcUtil.queryForObj(queryGet, new Object[]{obj.getId()} , rm);
    }

    @Override
    public Recapito delete(Recapito obj) throws DataAccessException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Recapito getAll() throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
    
}
