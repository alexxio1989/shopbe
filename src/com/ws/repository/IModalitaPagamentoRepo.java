package com.ws.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ws.models.ModalitaPagamento;
import com.ws.repository.core.IRepository;
import com.ws.response.ModPagamentoResponse;

public interface IModalitaPagamentoRepo extends IRepository<ModalitaPagamento, ModPagamentoResponse>{
	public ModPagamentoResponse save(List<ModalitaPagamento> obj) throws DataAccessException, SQLException;
	public ModPagamentoResponse update(List<ModalitaPagamento> obj) throws DataAccessException, SQLException;
	public ModPagamentoResponse deleteAll(int id) throws DataAccessException, SQLException;
	public ModPagamentoResponse getAll(int id) throws DataAccessException, SQLException ;
}
