package com.ws.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.GiornoLavorativo;

@Component
public class GiornoLavorativoRowMapper implements RowMapper<GiornoLavorativo> {

	

    @Override
    public GiornoLavorativo mapRow(ResultSet rs, int rowNum) throws SQLException {
    	GiornoLavorativo giornoLavorativo = new GiornoLavorativo();
    	giornoLavorativo.setId(rs.getInt("idgiorni_chiusura"));
    	giornoLavorativo.setIdNegozio(rs.getInt("negozio_idnegozio"));
    	giornoLavorativo.setDay(rs.getInt("day"));
    	giornoLavorativo.setDescrizione(rs.getString("descrizione"));
    	giornoLavorativo.setOrarioApertura(rs.getString("orario_apertura"));
    	giornoLavorativo.setOrarioPausaInizio(rs.getString("orario_pausa_inizio"));
    	giornoLavorativo.setOrarioPausaFine(rs.getString("orario_pausa_fine"));
    	giornoLavorativo.setOrarioChiusura(rs.getString("orario_chiusura"));
    	giornoLavorativo.setChiuso(rs.getBoolean("chiuso"));
        return giornoLavorativo;
    }
    
}
