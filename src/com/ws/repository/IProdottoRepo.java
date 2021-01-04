package com.ws.repository;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.ws.models.Prodotto;
import com.ws.models.SubDominio;
import com.ws.repository.core.IRepository;
import com.ws.response.ProdottoResponse;

public interface IProdottoRepo extends IRepository<Prodotto,ProdottoResponse> {

    public Prodotto get(int id) throws DataAccessException, SQLException;
    
    public boolean deleteBySubTipo(SubDominio subDominio); 
    
}
