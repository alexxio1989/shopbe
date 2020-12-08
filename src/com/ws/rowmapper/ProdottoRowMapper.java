package com.ws.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Prodotto;
import com.ws.models.SubDominio;
import com.ws.repository_impl.SottoTipoRepoImpl;
import com.ws.utils.JdbcUtil;

@Component
public class ProdottoRowMapper implements RowMapper<Prodotto> {

 
    
    @Autowired
    private JdbcUtil jdbcUtil;
    
    @Autowired
    private SottoTipoRowMapperLite trrm;
    
    @Value("${sotto.tipo.get}")
    protected String queryGet;


    @Override
    public Prodotto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Prodotto prodotto = new Prodotto();
        prodotto.setId(rs.getInt("idprodotto"));
        prodotto.setNomeProdotto(rs.getString("nome_prodotto"));
        prodotto.setDescrizione(rs.getString("descrizione"));
        prodotto.setImage(rs.getString("image"));
        prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
        prodotto.setTipo(jdbcUtil.queryForObj(queryGet, new Object[] {rs.getInt("sotto_tipo_idsotto_tipo")} , trrm));
        return prodotto;
    }
    
}
