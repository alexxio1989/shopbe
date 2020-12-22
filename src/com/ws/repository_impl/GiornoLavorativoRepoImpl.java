package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.models.GiornoLavorativo;
import com.ws.repository.IGiornoLavorativoRepo;
import com.ws.repository.repoenums.Metodi.EnumMetodi;
import com.ws.response.GiornoLavorativoResponse;
import com.ws.response.enumresponse.ResponseStatus.EnumResponseStatus;
import com.ws.rowmapper.GiornoLavorativoRowMapper;
import com.ws.utils.JdbcUtil;

@Repository
public class GiornoLavorativoRepoImpl implements IGiornoLavorativoRepo{

    @Value("${giorni.lavorativi.save}")
    protected String querySave;

    @Value("${giorni.lavorativi.delete}")
    protected String queryDelete;

    @Value("${giorni.lavorativi.update}")
    protected String queryUpdate;

    @Value("${giorni.lavorativi.get.all}")
    protected String queryGetAll;

    @Autowired
    public JdbcUtil jdbcUtil;
    
    @Autowired
    private GiornoLavorativoRowMapper rm;

	@Override
	public GiornoLavorativoResponse save(GiornoLavorativo obj){
		// TODO Auto-generated method stub
		return getAll();
	}
	
	@Override
	public GiornoLavorativoResponse save(List<GiornoLavorativo> obj){
		GiornoLavorativoResponse giornoLavorativoResponse = null;
		if(obj != null && obj.size() > 0) {
			try {
				giornoLavorativoResponse = new GiornoLavorativoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.SAVE));
				for (GiornoLavorativo gl : obj) {
						jdbcUtil.update(querySave, new Object[] {gl.getIdNegozio() , gl.getDay() , gl.getDescrizione() , gl.getOrarioApertura() , gl.getOrarioPausaInizio() , gl.getOrarioPausaFine() , gl.getOrarioChiusura() , gl.isChiuso() ? 0 : 1});
				}
			} catch (DataAccessException | SQLException e) {
				giornoLavorativoResponse = new GiornoLavorativoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.SAVE));
				e.printStackTrace();
			}
			
		}
		return getAll(obj != null && obj.size() > 0 ? obj.get(0).getIdNegozio() : 0 , giornoLavorativoResponse);
	}

	@Override
	public GiornoLavorativoResponse update(GiornoLavorativo obj) {
		return null;
	}

	@Override
	public GiornoLavorativoResponse get(GiornoLavorativo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GiornoLavorativoResponse getAll()  {
		GiornoLavorativoResponse giornoLavorativoResponse = new GiornoLavorativoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        return getAll(giornoLavorativoResponse);
	}

	private GiornoLavorativoResponse getAll(GiornoLavorativoResponse giornoLavorativoResponse){
		List<GiornoLavorativo> list;
		try {
			list = jdbcUtil.query(queryGetAll , rm);
			giornoLavorativoResponse.setGiorniLavorativi(list);
		} catch (DataAccessException | SQLException e) {
			giornoLavorativoResponse = new GiornoLavorativoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.GET_ERROR));
			e.printStackTrace();
		}
		return giornoLavorativoResponse;
	}

	@Override
	public GiornoLavorativoResponse delete(GiornoLavorativo obj) {
		// TODO Auto-generated method stub
		return getAll();
	}

	@Override
	public GiornoLavorativoResponse deleteAll(int idNegozio){
		// TODO Auto-generated method stub
		return getAll(idNegozio);
	}

	@Override
	public GiornoLavorativoResponse getAll(int idNegozio){
		GiornoLavorativoResponse giornoLavorativoResponse = new GiornoLavorativoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        return getAll(idNegozio, giornoLavorativoResponse);
	}

	private GiornoLavorativoResponse getAll(int idNegozio, GiornoLavorativoResponse giornoLavorativoResponse) {
		List<GiornoLavorativo> list;
		try {
			list = jdbcUtil.query(queryGetAll ,new Object[] {idNegozio} , rm);
			giornoLavorativoResponse.setGiorniLavorativi(list);
		} catch (DataAccessException | SQLException e) {
			giornoLavorativoResponse = new GiornoLavorativoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.GET_ERROR));
			e.printStackTrace();
		}
		return giornoLavorativoResponse;
	}

	@Override
	public GiornoLavorativoResponse update(List<GiornoLavorativo> obj){
		GiornoLavorativoResponse giornoLavorativoResponse =  null;
		if(obj != null && obj.size() > 0) {
			try {
				for (GiornoLavorativo gl : obj) {
					int day = gl.getDay();
					String descrizione = gl.getDescrizione();
					String orarioApertura = gl.getOrarioApertura();
					String orarioPausaInizio = gl.getOrarioPausaInizio();
					String orarioPausaFine = gl.getOrarioPausaFine();
					String orarioChiusura = gl.getOrarioChiusura();
					int chiuso = gl.isChiuso() ? 1 : 0;
					int idNegozio = gl.getIdNegozio();
					int id = gl.getId();
						jdbcUtil.update(queryUpdate, new Object[] {day , descrizione , orarioApertura , orarioPausaInizio , orarioPausaFine , orarioChiusura , chiuso , idNegozio , id} );
				}
				giornoLavorativoResponse = new GiornoLavorativoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.UPDATE));
			} catch (DataAccessException | SQLException e) {
				giornoLavorativoResponse = new GiornoLavorativoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.UPDATE));
				e.printStackTrace();
			}
			
		}
		return getAll(obj != null && obj.size() > 0 ? obj.get(0).getIdNegozio() : 0,giornoLavorativoResponse);
	}

   
    
}
