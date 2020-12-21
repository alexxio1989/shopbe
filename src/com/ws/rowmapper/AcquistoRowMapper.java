package com.ws.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.enums.StatusAcquisto.EnumStatusAcquisto;
import com.ws.models.Acquisto;
import com.ws.repository_impl.ModalitaPagamentoRepoImpl;
import com.ws.repository_impl.NegozioRepoImpl;
import com.ws.repository_impl.ProdottoRepoImpl;
import com.ws.repository_impl.UtenteRempoImpl;
import com.ws.utils.JdbcUtil;

@Component
public class AcquistoRowMapper implements RowMapper<Acquisto> {
	
	@Value("${acquisto.get.all}")
    protected String queryGet;
    
    @Value("${acquisto.get.all.utente}")
    protected String queryGetUtente;
    
    @Autowired
    protected ProdottoRepoImpl prodottoRepoImpl;
   
    @Autowired
    protected UtenteRempoImpl utenteRempoImpl;
    
    @Autowired
    protected ModalitaPagamentoRepoImpl modalitaPagamentoRepoImpl;
    
    @Autowired
    protected NegozioRepoImpl negozioRepoImpl;

    @Override
    public Acquisto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Acquisto acquisto = new Acquisto();
        acquisto.setId(rs.getInt("idacquisto"));
        acquisto.setTotale(rs.getBigDecimal("totale"));
        acquisto.setUtente(utenteRempoImpl.get(rs.getInt("utente_idutente")));
        acquisto.setProdotto(prodottoRepoImpl.get(rs.getInt("prodotto_idprodotto")));
        acquisto.setCodiceAquisto(rs.getString("codice_acquisto"));
        acquisto.setModalitaPagamento(modalitaPagamentoRepoImpl.get(rs.getInt("modalita_pagamento_idmodalita_pagamento")));
        acquisto.setDataAcquisto(rs.getDate("data_acquisto"));
        acquisto.setDataRitiro(rs.getDate("data_ritiro"));
        acquisto.setQnt(rs.getBigDecimal("qnt"));
        if(rs.getInt("idNegozio_ritiro") > 0) {
        	acquisto.setNegozioRitiro(negozioRepoImpl.get(rs.getInt("idNegozio_ritiro")));
        }
        acquisto.setDataCosegnaPrevista(rs.getDate("data_consegna_prevista"));
        acquisto.setStatus(EnumStatusAcquisto.getStatus(rs.getString("status")));
        return acquisto;
    }
    
}
