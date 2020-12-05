package com.ws.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Recapito;
import com.ws.models.Utente;
import com.ws.repository.IRecapitoRepo;
import com.ws.utils.JdbcUtil;

@Component
public class UtenteRowMapper implements RowMapper<Utente> {

    @Value("${utente.get.tipo.utente}")
    protected String queryGetTipoUtente;

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private TipoRowMapper trrm;

    @Autowired
    private IRecapitoRepo<Recapito,Recapito>  recapitoRepo;

    @Override
    public Utente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Utente utente = new Utente();
        utente.setId(rs.getInt("idutente"));
        utente.setNome(rs.getString("nome"));
        utente.setCognome(rs.getString("cognome"));
        utente.setEmail(rs.getString("email"));
        utente.setTipoUtente(jdbcUtil.queryForObj(queryGetTipoUtente, new Object[]{rs.getInt("tipo_utente_idtipo_utente")} , trrm));
        Recapito obj = new Recapito();
        obj.setId(rs.getInt("recapito_idrecapito"));
        utente.setRecapito(recapitoRepo.get(obj));
        return utente;
    }

}
