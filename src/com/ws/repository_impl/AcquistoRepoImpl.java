package com.ws.repository_impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ws.models.Acquisto;
import com.ws.repository.IAcquistoRepo;
import com.ws.response.AcquistoResponse;

@Repository
public class AcquistoRepoImpl implements IAcquistoRepo{

    @Value("${acquisto.save}")
    protected String querySave;

    @Value("${acquisto.delete}")
    protected String queryDelete;

    @Value("${acquisto.update}")
    protected String queryUpdate;

    @Value("${acquisto.get}")
    protected String queryGet;

    @Override
    public AcquistoResponse save(Acquisto obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AcquistoResponse update(Acquisto obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AcquistoResponse get(Acquisto obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AcquistoResponse delete(Acquisto obj) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public AcquistoResponse getAll() throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
    
}
