package com.ws.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.controller.core.IController;
import com.ws.models.GiornoLavorativo;
import com.ws.models.ModalitaPagamento;
import com.ws.repository_impl.ModalitaPagamentoRepoImpl;
import com.ws.response.GiornoLavorativoResponse;
import com.ws.response.ModPagamentoResponse;
import com.ws.utils.Utils;

@RestController
@RequestMapping("/modpagamento")
public class ModPagamentoController implements IController<List<ModalitaPagamento>, ModPagamentoResponse>{
	@Autowired
	private ModalitaPagamentoRepoImpl repo;

	@Override
	public ResponseEntity<ModPagamentoResponse> save(List<ModalitaPagamento> obj) throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ModPagamentoResponse> update(List<ModalitaPagamento> obj) throws DataAccessException, SQLException {
		ModPagamentoResponse res = repo.update(obj);
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

	@Override
	public ResponseEntity<ModPagamentoResponse> get(List<ModalitaPagamento> obj) throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ModPagamentoResponse> delete(List<ModalitaPagamento> obj) throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ModPagamentoResponse> getAll() throws DataAccessException, SQLException {
		ModPagamentoResponse res = repo.getAll();
		return Utils.getResponseEntity(res, res.getHttpStatus());
	}

}
