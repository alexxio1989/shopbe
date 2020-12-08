package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.models.Prodotto;
import com.ws.repository.IProdottoRepo;
import com.ws.repository.repoenums.Metodi.EnumMetodi;
import com.ws.response.ProdottoResponse;
import com.ws.response.enumresponse.ResponseStatus.EnumResponseStatus;
import com.ws.rowmapper.ProdottoRowMapper;
import com.ws.utils.JdbcUtil;

@Repository
public class ProdottoRepoImpl implements IProdottoRepo {

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
    public ProdottoResponse save(Prodotto obj) throws DataAccessException, SQLException {
        try {
            jdbcUtil.update(querySave,new Object[] { obj.getNomeProdotto(), 
                                                     obj.getDescrizione(),
                                                     obj.getImage(), 
                                                     obj.getPrezzo() , 
                                                     obj.getTipo().getId() });
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAll();
    }

    @Override
    public ProdottoResponse update(Prodotto obj) throws DataAccessException, SQLException {
        try {
            jdbcUtil.update(queryUpdate,new Object[] { obj.getNomeProdotto(), 
                                                     obj.getDescrizione(),
                                                     obj.getImage(), 
                                                     obj.getPrezzo() , obj.getId() });
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAll();
    }

    @Override
    public ProdottoResponse get(Prodotto obj) throws DataAccessException, SQLException {
    	ProdottoResponse prodottoResponse = new ProdottoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
    	prodottoResponse.setProdotto(jdbcUtil.queryForObj(queryGet, new Object[] {obj.getId()}, rm));
        return prodottoResponse;
    }

    @Override
    public ProdottoResponse delete(Prodotto obj) throws DataAccessException, SQLException {
        try {
            jdbcUtil.update(queryDelete,new Object[] { obj.getId() });
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAll();
    }

    @Override
    public Prodotto get(int id) throws DataAccessException, SQLException {
        return jdbcUtil.queryForObj(queryGet, new Object[]{id} , rm);
    }

	@Override
	public ProdottoResponse getAll() throws DataAccessException, SQLException {
		ProdottoResponse prodottoResponse = new ProdottoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        List<Prodotto> list = jdbcUtil.query(queryGetAll , rm);
        prodottoResponse.setList(list);
        return prodottoResponse;
	}
}
