package com.ws.repository;

import java.util.List;

import com.ws.models.Acquisto;
import com.ws.models.Prodotto;
import com.ws.repository.core.IRepository;
import com.ws.response.AcquistoResponse;


public interface IAcquistoRepo extends IRepository<Acquisto,AcquistoResponse> {
	
	public List<Acquisto> getAllUtente(int id);
	
}