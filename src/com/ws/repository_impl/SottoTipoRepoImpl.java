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
import com.ws.utils.Utils;

@Repository
public class SottoTipoRepoImpl implements ISottoTipoRepo{

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
    public SubDominio save(SubDominio obj)  {
    	String code = Utils.createCode(obj.getDescrizione());
        int id = 0;
		try {
			id = jdbcUtil.saveAndGetId(new Object[] { code,obj.getDescrizione(), obj.getTipoPadre().getId()},querySave);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return get(id);
    }

    @Override
    public SubDominio update(SubDominio obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubDominio get(SubDominio obj)  {
        int id = obj.getId();
        return get(id);
    }

    public SubDominio get(int id)  {
        try {
			return jdbcUtil.queryForObj(queryGet, new Object[] {id} , trrm);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    @Override
    public SubDominio delete(SubDominio obj) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public SubDominio getAll()  {
		// TODO Auto-generated method stub
		return null;
	}
    
}
