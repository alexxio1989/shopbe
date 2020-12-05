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
public class TipoRowMapper implements RowMapper<Dominio> {
	
	@Value("${sotto.tipo.get.sotto.tipi}")
    protected String queryGetSottoTipi;
	
	@Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private SottoTipoRowMapper rm;

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
        List<SubDominio> list = jdbcUtil.query(queryGetSottoTipi ,new Object[] {rs.getInt("idtipo")}, rm);
        dominio.setSottoTipi(list);
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
