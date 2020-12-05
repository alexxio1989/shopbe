package com.ws.repository_impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ws.models.SubDominio;
import com.ws.repository.ISottoTipoRepo;
import com.ws.rowmapper.SottoTipoRowMapper;
import com.ws.utils.JdbcUtil;

@Repository
public class SottoTipoRepoImpl implements ISottoTipoRepo<SubDominio,SubDominio> {

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private SottoTipoRowMapper trrm;

    @Value("${sotto.tipo.save}")
    protected String querySave;

    @Value("${sotto.tipo.delete}")
    protected String queryDelete;

    @Value("${sotto.tipo.update}")
    protected String queryUpdate;

    @Value("${sotto.tipo.get}")
    protected String queryGet;

    @Override
    public SubDominio save(SubDominio obj) throws DataAccessException, SQLException {
        int id = jdbcUtil.saveAndGetId(new Object[] { obj.getCodice(),obj.getDescrizione(), obj.getTipoPadre().getId()},querySave);
        return get(id);
    }

    @Override
    public SubDominio update(SubDominio obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubDominio get(SubDominio obj) throws DataAccessException, SQLException {
        int id = obj.getId();
        return get(id);
    }

    private SubDominio get(int id) throws DataAccessException, SQLException {
        return jdbcUtil.queryForObj(queryGet, new Object[] {id} , trrm);
    }

    @Override
    public SubDominio delete(SubDominio obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
