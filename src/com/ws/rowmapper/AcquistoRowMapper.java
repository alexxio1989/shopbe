package com.ws.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Acquisto;

@Component
public class AcquistoRowMapper implements RowMapper<Acquisto> {

    @Override
    public Acquisto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Acquisto acquisto = new Acquisto();
        return acquisto;
    }
    
}
