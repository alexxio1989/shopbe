package com.ws.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.Magazino;
import com.ws.models.Prodotto;
import com.ws.repository_impl.ProdottoRepoImpl;

@Component
public class MagazinoRowMapper implements RowMapper<Magazino> {

    private ProdottoRepoImpl prodottoRepo;

    @Override
    public Magazino mapRow(ResultSet rs, int rowNum) throws SQLException {
        Magazino magazino = new Magazino();
        magazino.setIdNegozio(rs.getInt("negozio_idnegozio"));
        Prodotto prodotto = prodottoRepo.get(rs.getInt("prodotto_idprodotto"));
        prodotto.setQntRimanente(rs.getInt("quantity"));
        magazino.setProdottoSelected(prodotto);
        return magazino;
    }
    
}
