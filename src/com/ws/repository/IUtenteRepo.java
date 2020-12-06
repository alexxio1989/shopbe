package com.ws.repository;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.ws.models.Utente;
import com.ws.repository.core.IRepository;
import com.ws.response.UtenteResponse;

public interface IUtenteRepo extends IRepository<Utente,UtenteResponse>{

    public UtenteResponse login(Utente obj) throws DataAccessException, SQLException ;

}
