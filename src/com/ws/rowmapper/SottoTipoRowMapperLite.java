package com.ws.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.SubDominio;

@Component
public class SottoTipoRowMapperLite implements RowMapper<SubDominio> {
	
	
	@Value("${get.prodotti.associati}")
    protected String queryGetProdottiAssociati;

    @Override
    public SubDominio mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubDominio dominio = new SubDominio();
        dominio.setId(rs.getInt("idsotto_tipo"));
        dominio.setCodice(rs.getString("codice_figlio"));
        dominio.setDescrizione(rs.getString("descrizione_figlio"));
        dominio.setIdPadre(rs.getInt("tipo_idtipo"));
        return dominio;
    }
    
}
