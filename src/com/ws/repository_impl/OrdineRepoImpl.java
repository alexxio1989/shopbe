package com.ws.repository_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ws.models.Ordine;
import com.ws.repository.IOrdineRepo;
import com.ws.response.OrdineResponse;
import com.ws.utils.JdbcUtil;

@Repository
public class OrdineRepoImpl implements IOrdineRepo<Ordine,OrdineResponse> {

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
    public ResponseEntity<OrdineResponse> save(Ordine obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<OrdineResponse> update(Ordine obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<OrdineResponse> get(Ordine obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<OrdineResponse> delete(Ordine obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
