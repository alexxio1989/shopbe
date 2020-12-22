package com.ws.repository;

import java.util.List;

import com.ws.models.GiornoLavorativo;
import com.ws.repository.core.IRepository;
import com.ws.response.GiornoLavorativoResponse;

public interface IGiornoLavorativoRepo extends IRepository<GiornoLavorativo, GiornoLavorativoResponse>{
	
	public GiornoLavorativoResponse save(List<GiornoLavorativo> obj)  ;
	public GiornoLavorativoResponse update(List<GiornoLavorativo> obj)  ;
	public GiornoLavorativoResponse deleteAll(int idNegozio)  ;
	public GiornoLavorativoResponse getAll(int idNegozio)  ;

}
