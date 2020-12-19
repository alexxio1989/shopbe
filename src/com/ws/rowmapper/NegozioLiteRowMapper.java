package com.ws.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Negozio;
import com.ws.models.Recapito;
import com.ws.repository.IRecapitoRepo;

@Component
public class NegozioLiteRowMapper implements RowMapper<Negozio> {
    
    @Autowired
    private IRecapitoRepo recapitoRepo;

    @Override
    public Negozio mapRow(ResultSet rs, int rowNum) throws SQLException {
        Negozio negozio = new Negozio();
        negozio.setId(rs.getInt("idnegozio"));
        negozio.setNome(rs.getString("nome"));
        Recapito obj = new Recapito();
        obj.setId(rs.getInt("recapito_idrecapito"));
        negozio.setRecapito(recapitoRepo.get(obj));
        return negozio;
    }
    
}
