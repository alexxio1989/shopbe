package com.ws.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Prodotto;
import com.ws.models.SubDominio;
import com.ws.utils.JdbcUtil;

@Component
public class SottoTipoRowMapper implements RowMapper<SubDominio> {
	
	@Autowired
    private JdbcUtil jdbcUtil;
	
	@Autowired
	private ProdottoRowMapper rm;
	
	@Value("${get.prodotti.associati}")
    protected String queryGetProdottiAssociati;

    @Override
    public SubDominio mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubDominio dominio = new SubDominio();
        dominio.setId(rs.getInt("idsotto_tipo"));
        dominio.setCodice(rs.getString("codice_figlio"));
        dominio.setDescrizione(rs.getString("descrizione_figlio"));
        List<Prodotto> prodottiAssociati = jdbcUtil.query(queryGetProdottiAssociati ,new Object[] {rs.getInt("idsotto_tipo")} ,  rm);;
		dominio.setProdottiAssociati(prodottiAssociati);
        return dominio;
    }
    
}
