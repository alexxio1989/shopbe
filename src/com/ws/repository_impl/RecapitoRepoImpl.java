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
    public Recapito save(Recapito obj)  {
        int id;
        Recapito findRecapito = new Recapito();
		try {
			id = jdbcUtil.saveAndGetId(new Object[] {obj.getIndirizzo(),obj.getZip(),obj.getTelefono(),obj.getCitta()}, querySave);
			findRecapito.setId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return get(findRecapito);
    }

    @Override
    public Recapito update(Recapito obj)  {
        return null;
    }

    @Override
    public Recapito get(Recapito obj)  {
        try {
			return jdbcUtil.queryForObj(queryGet, new Object[]{obj.getId()} , rm);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
    }

    @Override
    public Recapito delete(Recapito obj)  {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Recapito getAll()  {
		// TODO Auto-generated method stub
		return null;
	}
    
}
