package com.ws.repository_impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ws.models.Acquisto;
import com.ws.repository.IAcquistoRepo;
import com.ws.response.AcquistoResponse;

@Repository
public class AcquistoRepoImpl implements IAcquistoRepo<Acquisto,AcquistoResponse> {

    @Value("${acquisto.save}")
    protected String querySave;

    @Value("${acquisto.delete}")
    protected String queryDelete;

    @Value("${acquisto.update}")
    protected String queryUpdate;

    @Value("${acquisto.get}")
    protected String queryGet;

    @Override
    public ResponseEntity<AcquistoResponse> save(Acquisto obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<AcquistoResponse> update(Acquisto obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<AcquistoResponse> get(Acquisto obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<AcquistoResponse> delete(Acquisto obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
