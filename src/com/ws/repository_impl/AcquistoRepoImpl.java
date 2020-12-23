package com.ws.repository_impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.email.SendEmail;
import com.ws.enums.StatusAcquisto.EnumStatusAcquisto;
import com.ws.models.Acquisto;
import com.ws.models.Magazino;
import com.ws.models.Prodotto;
import com.ws.repository.IAcquistoRepo;
import com.ws.repository.repoenums.Metodi.EnumMetodi;
import com.ws.response.AcquistoResponse;
import com.ws.response.enumresponse.ResponseStatus.EnumResponseStatus;
import com.ws.rowmapper.AcquistoRowMapper;
import com.ws.utils.JdbcUtil;

@Repository
public class AcquistoRepoImpl implements IAcquistoRepo{

    @Value("${acquisto.save}")
    protected String querySave;

    @Value("${acquisto.delete}")
    protected String queryDelete;

    @Value("${acquisto.update}")
    protected String queryUpdate;

    @Value("${acquisto.get.all}")
    protected String queryGet;
    
    @Value("${acquisto.get.all.utente}")
    protected String queryGetUtente;
    
    @Autowired
    protected MagazinoRepoImpl magazinoRepoImpl;
    
    @Autowired
    protected JdbcUtil jdbcUtil;
    
    @Autowired
    protected AcquistoRowMapper rm;
  
    @Autowired
    protected SendEmail email;
    
    
    public String generateCode() {
    	 
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }
    
    

    @Override
    public AcquistoResponse save(Acquisto obj){
		AcquistoResponse acquistoResponse ;

    	
    	String codice_acquisto = generateCode();
    	obj.setCodiceAquisto(obj.getUtente().getId() + codice_acquisto.toUpperCase());
    	obj.setStatus(EnumStatusAcquisto.getStatus("DC"));
		try {
			acquistoResponse = new AcquistoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.SAVE));
	    	for (Prodotto prodotto : obj.getProdotti()) {
    		
    		int prodotto_idprodotto = prodotto.getId();
    		int utente_idutente = obj.getUtente().getId();
    		BigDecimal totale = obj.getTotale();
    		int modalita_pagamento_idmodalita_pagamento = obj.getModalitaPagamento().getId();
    		Date data_acquisto = new Date();
    		Date data_ritiro = obj.getDataRitiro();
    		int idNegozio_ritiro =  obj.getNegozioRitiro() != null ? obj.getNegozioRitiro().getId() : 0;
    		Date data_consegna_prevista = null;
    		BigDecimal qnt = prodotto.getQnt();
    		
				jdbcUtil.update(querySave, new Object[] {prodotto_idprodotto,utente_idutente,totale,codice_acquisto,modalita_pagamento_idmodalita_pagamento,data_acquisto,data_ritiro,idNegozio_ritiro,data_consegna_prevista,EnumStatusAcquisto.DA_CONFERMARE.getCode(),qnt});
				Magazino magazzino = new Magazino();
				prodotto.getQntRimanente();
				
				prodotto.setQntRimanente(prodotto.getQntRimanente().subtract(qnt));
				magazzino.setProdottoSelected(prodotto);
				magazzino.setIdNegozio(prodotto.getIdNegozio());
				magazinoRepoImpl.update(magazzino );
		    }
	    	email.sendEmailAquisto(obj);
	    	return getAll(acquistoResponse);
    	} catch (DataAccessException | SQLException e) {
    		acquistoResponse = new AcquistoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.SAVE_ERROR));
    		e.printStackTrace();
    	}
		return acquistoResponse;


    }

    @Override
    public AcquistoResponse update(Acquisto obj){
    	AcquistoResponse acquistoResponse = new AcquistoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.UPDATE));
    	try {
			jdbcUtil.update(queryUpdate, new Object[] {obj.getDataCosegnaPrevista() , obj.getStatus().getCodice() , obj.getCodiceAquisto()});
			email.sendEmailAggiornaAcquisto(obj);
			return getAll(acquistoResponse);
		} catch (DataAccessException | SQLException e) {
			acquistoResponse = new AcquistoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.UPDATE_ERROR));
			e.printStackTrace();
		}
		return acquistoResponse;
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
	public AcquistoResponse getAll(){

		AcquistoResponse acquistoResponse = new AcquistoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
		
        return getAll(acquistoResponse);
	}



	private AcquistoResponse getAll(AcquistoResponse acquistoResponse) {
		List<Acquisto> listAquisti;
		try {
			listAquisti = jdbcUtil.query(queryGet, rm);
			List<Acquisto> newList = getListAcquisti(listAquisti);
			acquistoResponse.setList(newList);
			
			acquistoResponse.setListStatus(EnumStatusAcquisto.getAll());
		} catch (DataAccessException | SQLException e) {
			acquistoResponse = new AcquistoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.GET_ERROR));
			e.printStackTrace();
		}
		

		return acquistoResponse;
	}



	@Override
	public List<Acquisto> getAllUtente(int id) {
		
		List<Acquisto> listAquisti = null;
		try {
			listAquisti = jdbcUtil.query(queryGetUtente, new Object[]{id} , rm);
			List<Acquisto> newList = getListAcquisti(listAquisti);
			return newList;
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAquisti;
		
		

	}



	private List<Acquisto> getListAcquisti(List<Acquisto> listAquisti) {
		List<Acquisto> newList = new ArrayList<Acquisto>();
		
		
		Map<String , Acquisto> mapCodeAcquisto = new ConcurrentHashMap<String, Acquisto>();
		
		for (Acquisto acquisto : listAquisti) {
			acquisto.getProdotto().setQnt(acquisto.getQnt());
			
			if(mapCodeAcquisto.size() > 0) {
				if(mapCodeAcquisto.keySet().stream().anyMatch(c -> c.equalsIgnoreCase(acquisto.getCodiceAquisto()))) {
					Acquisto acquistoRetrieved = mapCodeAcquisto.get(acquisto.getCodiceAquisto());
					if(acquistoRetrieved.getProdotti().stream().filter(p -> p.getId() == acquisto.getProdotto().getId()).count() == 0) {
						acquistoRetrieved.getProdotti().add(acquisto.getProdotto());
					}
				} else {
					if(acquisto.getProdotti().stream().filter(p -> p.getId() == acquisto.getProdotto().getId()).count() == 0) {
						acquisto.getProdotti().add(acquisto.getProdotto());
					}
					mapCodeAcquisto.put(acquisto.getCodiceAquisto(), acquisto);
				}

				
			} else {
				if(acquisto.getProdotti().stream().filter(p -> p.getId() == acquisto.getProdotto().getId()).count() == 0) {
					acquisto.getProdotti().add(acquisto.getProdotto());
				}
				mapCodeAcquisto.put(acquisto.getCodiceAquisto(), acquisto);
			}
			
		}
		
		for (Acquisto acquisto : mapCodeAcquisto.values()) {
			newList.add(acquisto);
		}
		return newList;
	}
    
}
