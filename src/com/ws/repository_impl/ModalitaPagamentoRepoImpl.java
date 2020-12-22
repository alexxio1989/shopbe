package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

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
    
    @Value("${mod.pagamento.get}")
    protected String queryGet;
    
    @Autowired
    public JdbcUtil jdbcUtil;

	@Override
	public ModPagamentoResponse save(ModalitaPagamento obj)  {
		return null;
	}

	@Override
	public ModPagamentoResponse update(ModalitaPagamento obj)  {
		ModPagamentoResponse modPagamentoResponse = null;
		try {
			jdbcUtil.update(queryUpdate, new Object[] {obj.getDescrizione() , obj.isEnable() , obj.getId() } );
			modPagamentoResponse = new ModPagamentoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.UPDATE));
		} catch (DataAccessException | SQLException e) {
			modPagamentoResponse = new ModPagamentoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.UPDATE_ERROR));
			e.printStackTrace();
		}

		return getAll(modPagamentoResponse);
	}

	@Override
	public ModPagamentoResponse get(ModalitaPagamento obj)  {
		return null;
	}

	@Override
	public ModPagamentoResponse getAll()  {
		ModPagamentoResponse modPagamentoResponse = new ModPagamentoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        return getAll(modPagamentoResponse);
	}

	private ModPagamentoResponse getAll(ModPagamentoResponse modPagamentoResponse){
		List<ModalitaPagamento> list;
		try {
			list = jdbcUtil.query(queryGetAll , rm);
			modPagamentoResponse.setList(list);
		} catch (DataAccessException | SQLException e) {
			modPagamentoResponse = new ModPagamentoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.GET_ERROR));

			e.printStackTrace();
		}
		return modPagamentoResponse;
	}

	@Override
	public ModPagamentoResponse delete(ModalitaPagamento obj)  {
		return null;
	}

	@Override
	public ModPagamentoResponse getAll(int id)  {
		return null;
	}

	@Override
	public ModPagamentoResponse save(List<ModalitaPagamento> obj)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModPagamentoResponse update(List<ModalitaPagamento> obj)  {
		ModPagamentoResponse modPagamentoResponse = null;
		if(obj != null && obj.size() > 0) {
			try {
				for (ModalitaPagamento mod : obj) {
						jdbcUtil.update(queryUpdate, new Object[] {mod.getDescrizione() , mod.isEnable() , mod.getId() } );
	
				}
				modPagamentoResponse = new ModPagamentoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.SAVE));

			} catch (DataAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getAll(modPagamentoResponse);
	}

	@Override
	public ModPagamentoResponse deleteAll(int id)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModalitaPagamento get(int id)  {
		try {
			return jdbcUtil.queryForObj(queryGet, new Object[] {id }, rm);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
