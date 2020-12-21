package com.ws.repository_impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.ws.models.Dominio;
import com.ws.models.Prodotto;
import com.ws.repository.IAcquistoRepo;
import com.ws.repository.repoenums.Metodi.EnumMetodi;
import com.ws.response.AcquistoResponse;
import com.ws.response.GiornoLavorativoResponse;
import com.ws.response.enumresponse.ResponseStatus.EnumResponseStatus;
import com.ws.rowmapper.AcquistoRowMapper;
import com.ws.rowmapper.GiornoLavorativoRowMapper;
import com.ws.rowmapper.ModPagamentoRowMapper;
import com.ws.rowmapper.ProdottoRowMapper;
import com.ws.rowmapper.UtenteRowMapper;
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
    public AcquistoResponse save(Acquisto obj) throws DataAccessException, SQLException {
    	
    	
    	String codice_acquisto = generateCode();
    	obj.setCodiceAquisto(codice_acquisto);
    	obj.setStatus(EnumStatusAcquisto.getStatus("DC"));
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
		}
    	email.sendEmailAquisto(obj);


        return getAll();
    }

    @Override
    public AcquistoResponse update(Acquisto obj) throws DataAccessException, SQLException {
    	jdbcUtil.update(querySave, new Object[] {obj.getDataCosegnaPrevista() , obj.getStatus().getCodice() , obj.getCodiceAquisto()});
        return getAll();
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

		AcquistoResponse acquistoResponse = new AcquistoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
		
        List<Acquisto> listAquisti = jdbcUtil.query(queryGet, rm);
		
		List<Acquisto> newList = getListAcquisti(listAquisti);
		acquistoResponse.setList(newList);

		return acquistoResponse;
	}



	@Override
	public List<Acquisto> getAllUtente(int id) throws DataAccessException, SQLException {
		
		List<Acquisto> listAquisti = jdbcUtil.query(queryGetUtente, new Object[]{id} , rm);
		
		List<Acquisto> newList = getListAcquisti(listAquisti);
		

		return newList;
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
