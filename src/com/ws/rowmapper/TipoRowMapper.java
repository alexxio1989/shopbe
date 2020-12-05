package com.ws.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Dominio;

@Component
public class TipoRowMapper implements RowMapper<Dominio> {

    @Override
    public Dominio mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dominio dominio = new Dominio();
        if(hasColumn(rs,"idtipo")){
            dominio.setId(rs.getInt("idtipo"));

        } else if (hasColumn(rs,"idcitta")){
            dominio.setId(rs.getInt("idcitta"));
        }
        dominio.setCodice(rs.getString("codice"));
        dominio.setDescrizione(rs.getString("descrizione"));
        return dominio;
    }
    
    public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }
}
