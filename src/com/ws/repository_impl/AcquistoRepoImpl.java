package com.ws.repository_impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.enums.StatusAcquisto.EnumStatusAcquisto;
import com.ws.models.Acquisto;
import com.ws.models.Prodotto;
import com.ws.repository.IAcquistoRepo;
import com.ws.repository.repoenums.Metodi.EnumMetodi;
import com.ws.response.AcquistoResponse;
import com.ws.response.GiornoLavorativoResponse;
import com.ws.response.enumresponse.ResponseStatus.EnumResponseStatus;
import com.ws.rowmapper.GiornoLavorativoRowMapper;
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
    
    @Autowired
    public JdbcUtil jdbcUtil;
    
    
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
    	
    	
    	for (Prodotto prodotto : obj.getProdotti()) {
    		
    		int prodotto_idprodotto = prodotto.getId();
    		int utente_idutente = obj.getIdUtente();
    		BigDecimal totale = obj.getTotale();
    		String codice_acquisto = generateCode();
    		int modalita_pagamento_idmodalita_pagamento = obj.getModalitaPagamento().getId();
    		Date data_acquisto = new Date();
    		Date data_ritiro = obj.getDataRitiro();
    		int idNegozio_ritiro =  obj.getNegozioRitiro() != null ? obj.getNegozioRitiro().getId() : 0;
    		Date data_consegna_prevista = null;
    		
    		jdbcUtil.update(querySave, new Object[] {prodotto_idprodotto,utente_idutente,totale,codice_acquisto,modalita_pagamento_idmodalita_pagamento,data_acquisto,data_ritiro,idNegozio_ritiro,data_consegna_prevista,EnumStatusAcquisto.DA_CONFERMARE.getCode()});
		}
       // INSERT INTO acquisto (


        return getAll();
    }

    @Override
    public AcquistoResponse update(Acquisto obj) {
        // TODO Auto-generated method stub
        return null;
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

		return acquistoResponse;
	}
    
}
