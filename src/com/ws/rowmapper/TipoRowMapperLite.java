package com.ws.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Dominio;
import com.ws.models.SubDominio;
import com.ws.utils.JdbcUtil;

@Component
public class TipoRowMapperLite implements RowMapper<Dominio> {
	
	
    @Override
    public Dominio mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dominio dominio = new Dominio();
        if(hasColumn(rs,"idtipo")){
            dominio.setId(rs.getInt("idtipo"));

        } 
        dominio.setCodice(rs.getString("codice_padre"));
        dominio.setDescrizione(rs.getString("descrizione_padre"));
        
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
