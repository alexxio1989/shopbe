package com.ws.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws.models.ModalitaPagamento;

@Component
public class ModPagamentoRowMapper implements RowMapper<ModalitaPagamento>  {

	@Override
	public ModalitaPagamento mapRow(ResultSet rs, int rowNum) throws SQLException {
		ModalitaPagamento modalitaPagamento = new ModalitaPagamento();
		modalitaPagamento.setId(rs.getInt("idmodalita_pagamento"));
		modalitaPagamento.setCodice(rs.getString("codice"));
		modalitaPagamento.setDescrizione(rs.getString("descrizione"));
		modalitaPagamento.setEnable(rs.getBoolean("enable"));
		return modalitaPagamento;
	}

}
