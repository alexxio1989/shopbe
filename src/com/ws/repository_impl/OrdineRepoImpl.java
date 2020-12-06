package com.ws.repository_impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ws.models.Ordine;
import com.ws.repository.IOrdineRepo;
import com.ws.response.OrdineResponse;
import com.ws.utils.JdbcUtil;

@Repository
public class OrdineRepoImpl implements IOrdineRepo{

    @Value("${ordine.save}")
    protected String querySave;

    @Value("${ordine.delete}")
    protected String queryDelete;

    @Value("${ordine.update}")
    protected String queryUpdate;

    @Value("${ordine.get}")
    protected String queryGet;

    @Autowired
    public JdbcUtil jdbcUtil;

    @Override
    public OrdineResponse save(Ordine obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrdineResponse update(Ordine obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrdineResponse get(Ordine obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrdineResponse delete(Ordine obj) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public OrdineResponse getAll() throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
    
}
