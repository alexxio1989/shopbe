package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ws.models.Magazino;
import com.ws.models.Prodotto;
import com.ws.repository.IMagazinoRepo;
import com.ws.rowmapper.MagazinoRowMapper;
import com.ws.utils.JdbcUtil;

@Repository
public class MagazinoRepoImpl implements IMagazinoRepo<Magazino, Magazino> {

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private MagazinoRowMapper rm;

    @Value("${magazino.save}")
    protected String querySave;

    @Value("${magazino.delete}")
    protected String queryDelete;

    @Value("${magazino.update}")
    protected String queryUpdate;

    @Value("${magazino.get}")
    protected String queryGet;

    @Override
    public Magazino save(Magazino obj) throws DataAccessException, SQLException {
        jdbcUtil.update(querySave, new Object[] {obj.getProdottoSelected().getId() , obj.getIdNegozio() , obj.getProdottoSelected().getQntRimanente()});
        return get(obj);
    }

    @Override
    public Magazino update(Magazino obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryUpdate, new Object[] {obj.getProdottoSelected().getQntRimanente() , obj.getProdottoSelected().getId() , obj.getIdNegozio()});
        return get(obj);
    }

    @Override
    public Magazino get(Magazino obj) throws DataAccessException, SQLException {
        Magazino newMagazino = new Magazino();
        List<Magazino> list = jdbcUtil.query(queryGet, new Object[]{obj.getIdNegozio()} , rm);
        if(list != null && list.size() > 0){
            newMagazino.setIdNegozio(list.get(0).getIdNegozio());
            List<Prodotto> newList = new ArrayList<>();
            for (Magazino magazino : list) {
                newList.add(magazino.getProdottoSelected());
            }
            newMagazino.setProdotti(newList);
        }
        return newMagazino;
    }

    @Override
    public Magazino delete(Magazino obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryDelete, new Object[] {obj.getProdottoSelected().getId() , obj.getIdNegozio() , obj.getProdottoSelected().getQntRimanente()});
        return get(obj);
    }

}
