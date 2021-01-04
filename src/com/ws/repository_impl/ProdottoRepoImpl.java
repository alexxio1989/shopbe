package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.models.Prodotto;
import com.ws.models.SubDominio;
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

	@Value("${prodotto.delete.by.sottotipo}")
	protected String queryDeleteBySottoTipo;

	@Value("${prodotto.update}")
	protected String queryUpdate;

	@Value("${prodotto.get}")
	protected String queryGet;

	@Value("${prodotto.get.all}")
	protected String queryGetAll;
	
	@Value("${count.prodotti.acquistati}")
	protected String queryCountProdottiAquistati;
	
	@Autowired
	private MagazinoRepoImpl magazinoRepoImpl;

	@Override
	public ProdottoResponse save(Prodotto obj) {
		ProdottoResponse prodottoResponse = null;
		try {

			jdbcUtil.update(querySave, new Object[] { obj.getNomeProdotto(), obj.getDescrizione(), obj.getImage(),
					obj.getPrezzo(), obj.getTipo().getId(), obj.getUnita(), obj.getStep() });
			prodottoResponse = new ProdottoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.SAVE));
		} catch (DataAccessException | SQLException e) {
			prodottoResponse = new ProdottoResponse(HttpStatus.BAD_REQUEST,
					EnumResponseStatus.getStatus(EnumMetodi.SAVE_ERROR));
			e.printStackTrace();
		}

		return getAll(prodottoResponse);
	}

	@Override
	public ProdottoResponse update(Prodotto obj) {
		ProdottoResponse prodottoResponse = null;
		try {
			jdbcUtil.update(queryUpdate, new Object[] { obj.getNomeProdotto(), obj.getDescrizione(), obj.getImage(),
					obj.getPrezzo(), obj.getUnita(), obj.getStep(), obj.getId() });
			prodottoResponse = new ProdottoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.UPDATE));
		} catch (DataAccessException | SQLException e) {
			prodottoResponse = new ProdottoResponse(HttpStatus.BAD_REQUEST,
					EnumResponseStatus.getStatus(EnumMetodi.UPDATE));
			e.printStackTrace();
		}
		return getAll(prodottoResponse);
	}

	@Override
	public ProdottoResponse get(Prodotto obj) {
		ProdottoResponse prodottoResponse = null;
		try {
			prodottoResponse = new ProdottoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
			prodottoResponse.setProdotto(jdbcUtil.queryForObj(queryGet, new Object[] { obj.getId() }, rm));
		} catch (DataAccessException | SQLException e) {
			prodottoResponse = new ProdottoResponse(HttpStatus.BAD_REQUEST,
					EnumResponseStatus.getStatus(EnumMetodi.GET));
			e.printStackTrace();
		}
		return prodottoResponse;
	}

	@Override
	public ProdottoResponse delete(Prodotto obj) {
		try {
			jdbcUtil.update(queryDelete, new Object[] { obj.getId() });
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getAll();
	}

	@Override
	public Prodotto get(int id) {
		try {
			return jdbcUtil.queryForObj(queryGet, new Object[] { id }, rm);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProdottoResponse getAll() {
		ProdottoResponse prodottoResponse = new ProdottoResponse(HttpStatus.OK,
				EnumResponseStatus.getStatus(EnumMetodi.GET));
		return getAll(prodottoResponse);
	}

	private ProdottoResponse getAll(ProdottoResponse prodottoResponse) {
		List<Prodotto> list;
		try {
			list = jdbcUtil.query(queryGetAll, rm);
			prodottoResponse.setList(list);
		} catch (DataAccessException | SQLException e) {
			prodottoResponse = new ProdottoResponse(HttpStatus.BAD_REQUEST,
					EnumResponseStatus.getStatus(EnumMetodi.GET));
			e.printStackTrace();
		}
		return prodottoResponse;
	}

	@Override
	public boolean deleteBySubTipo(SubDominio subDominio) {
		try {
			Integer queryForInteger = jdbcUtil.queryForInteger(queryCountProdottiAquistati, new Object[]{subDominio.getId()});
			if(queryForInteger == 0) {
				for (Prodotto iterable_element : subDominio.getProdottiAssociati()) {
					magazinoRepoImpl.delete(iterable_element);
				}
				jdbcUtil.update(queryDeleteBySottoTipo, new Object[] { subDominio.getId() });
				
			} else {
				throw new SQLException();
			}
			return true;
		} catch (DataAccessException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
