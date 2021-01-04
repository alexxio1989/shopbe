package com.ws.repository;

import com.ws.models.Magazino;
import com.ws.models.Prodotto;
import com.ws.repository.core.IRepository;

public interface IMagazinoRepo extends IRepository<Magazino, Magazino> {
	public boolean delete(Prodotto obj);
}
