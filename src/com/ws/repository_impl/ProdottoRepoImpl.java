package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ws.models.Prodotto;
import com.ws.repository.IProdottoRepo;
import com.ws.response.ProdottoResponse;
import com.ws.rowmapper.ProdottoRowMapper;
import com.ws.utils.JdbcUtil;
import com.ws.utils.Utils;

@Repository
public class ProdottoRepoImpl implements IProdottoRepo<Prodotto,ProdottoResponse> {

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private ProdottoRowMapper rm;

    @Value("${prodotto.save}")
    protected String querySave;

    @Value("${prodotto.delete}")
    protected String queryDelete;

    @Value("${prodotto.update}")
    protected String queryUpdate;

    @Value("${prodotto.get}")
    protected String queryGet;

    @Value("${prodotto.get.all}")
    protected String queryGetAll;

    @Override
    public ResponseEntity<ProdottoResponse> save(Prodotto obj) throws DataAccessException, SQLException {
        try {
            jdbcUtil.update(querySave,new Object[] { obj.getNomeProdotto(), 
                                                     obj.getDescrizione(),
                                                     obj.getImage(), 
                                                     obj.getPrezzo() , 
                                                     obj.getTipo().getCodice() });
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get(obj);
    }

    @Override
    public ResponseEntity<ProdottoResponse> update(Prodotto obj) throws DataAccessException, SQLException {
        try {
            jdbcUtil.update(queryUpdate,new Object[] { obj.getNomeProdotto(), 
                                                     obj.getDescrizione(),
                                                     obj.getImage(), 
                                                     obj.getPrezzo() });
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get(obj);
    }

    @Override
    public ResponseEntity<ProdottoResponse> get(Prodotto obj) throws DataAccessException, SQLException {
        ProdottoResponse prodottoResponse = new ProdottoResponse();
        List<Prodotto> list = jdbcUtil.query(queryGet , rm);
        prodottoResponse.setList(list);
        return Utils.getResponseEntity(prodottoResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProdottoResponse> delete(Prodotto obj) throws DataAccessException, SQLException {
        try {
            jdbcUtil.update(queryDelete,new Object[] { obj.getId() });
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get(obj);
    }

    @Override
    public Prodotto get(int id) throws DataAccessException, SQLException {
        return jdbcUtil.queryForObj(queryGet, new Object[]{id} , rm);
    }
}
