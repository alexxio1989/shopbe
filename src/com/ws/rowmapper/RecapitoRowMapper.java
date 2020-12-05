package com.ws.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Recapito;
import com.ws.utils.JdbcUtil;

@Component
public class RecapitoRowMapper implements RowMapper<Recapito> {

    @Value("${recapito.get.citta}")
    protected String queryGetCitta;

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private TipoRowMapper rm;

    @Override
    public Recapito mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recapito recapito = new Recapito();
        recapito.setIndirizzo(rs.getString("indirizzo"));
        recapito.setZip(rs.getInt("zip"));
        recapito.setCitta(jdbcUtil.queryForObj(queryGetCitta, new Object[]{rs.getInt("citta_idcitta")} , rm));
        recapito.setTelefono(rs.getString("telefono"));
        return recapito;
    }
}
