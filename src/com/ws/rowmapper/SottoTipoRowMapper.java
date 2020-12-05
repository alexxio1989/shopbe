package com.ws.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.SubDominio;

@Component
public class SottoTipoRowMapper implements RowMapper<SubDominio> {

    @Override
    public SubDominio mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubDominio dominio = new SubDominio();
        dominio.setId(rs.getInt("idsotto_tipo"));
        dominio.setCodice(rs.getString("codice"));
        dominio.setDescrizione(rs.getString("descrizione"));
        return dominio;
    }
    
}
