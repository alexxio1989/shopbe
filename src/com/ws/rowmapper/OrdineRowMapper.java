package com.ws.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Ordine;

@Component
public class OrdineRowMapper implements RowMapper<Ordine> {

    @Override
    public Ordine mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ordine ordine = new Ordine();
        return ordine;
    }
}
