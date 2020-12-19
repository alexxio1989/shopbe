package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.models.GiornoLavorativo;
import com.ws.models.ModalitaPagamento;
import com.ws.repository.IModalitaPagamentoRepo;
import com.ws.repository.repoenums.Metodi.EnumMetodi;
import com.ws.response.ModPagamentoResponse;
import com.ws.response.enumresponse.ResponseStatus.EnumResponseStatus;
import com.ws.rowmapper.ModPagamentoRowMapper;
import com.ws.utils.JdbcUtil;

@Repository
public class ModalitaPagamentoRepoImpl implements IModalitaPagamentoRepo{
	
	@Autowired
	private ModPagamentoRowMapper rm;
	
	@Value("${mod.pagamento.save}")
    protected String querySave;

    @Value("${mod.pagamento.delete}")
    protected String queryDelete;

    @Value("${mod.pagamento.update}")
    protected String queryUpdate;

    @Value("${mod.pagamento.get.all}")
    protected String queryGetAll;
    
    @Autowired
    public JdbcUtil jdbcUtil;

	@Override
	public ModPagamentoResponse save(ModalitaPagamento obj) throws DataAccessException, SQLException {
		return null;
	}

	@Override
	public ModPagamentoResponse update(ModalitaPagamento obj) throws DataAccessException, SQLException {
		jdbcUtil.update(queryUpdate, new Object[] {obj.getDescrizione() , obj.isEnable() , obj.getId() } );

		return getAll();
	}

	@Override
	public ModPagamentoResponse get(ModalitaPagamento obj) throws DataAccessException, SQLException {
		return null;
	}

	@Override
	public ModPagamentoResponse getAll() throws DataAccessException, SQLException {
		ModPagamentoResponse modPagamentoResponse = new ModPagamentoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        List<ModalitaPagamento> list = jdbcUtil.query(queryGetAll , rm);
        modPagamentoResponse.setList(list);
		return modPagamentoResponse;
	}

	@Override
	public ModPagamentoResponse delete(ModalitaPagamento obj) throws DataAccessException, SQLException {
		return null;
	}

	@Override
	public ModPagamentoResponse getAll(int id) throws DataAccessException, SQLException {
		return null;
	}

	@Override
	public ModPagamentoResponse save(List<ModalitaPagamento> obj) throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModPagamentoResponse update(List<ModalitaPagamento> obj) throws DataAccessException, SQLException {
		if(obj != null && obj.size() > 0) {
			for (ModalitaPagamento mod : obj) {
				jdbcUtil.update(queryUpdate, new Object[] {mod.getDescrizione() , mod.isEnable() , mod.getId() } );

			}
		}
		return getAll();
	}

	@Override
	public ModPagamentoResponse deleteAll(int id) throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
