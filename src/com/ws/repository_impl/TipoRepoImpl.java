package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.models.Dominio;
import com.ws.repository.ITipoRepo;
import com.ws.repository.repoenums.Metodi.EnumMetodi;
import com.ws.response.TipoResponse;
import com.ws.response.enumresponse.ResponseStatus.EnumResponseStatus;
import com.ws.rowmapper.TipoRowMapper;
import com.ws.utils.JdbcUtil;
import com.ws.utils.Utils;

@Repository
public class TipoRepoImpl implements ITipoRepo{

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private TipoRowMapper rm;

    @Value("${tipo.save}")
    protected String querySave;

    @Value("${tipo.delete}")
    protected String queryDelete;

    @Value("${tipo.update}")
    protected String queryUpdate;

    @Value("${tipo.get}")
    protected String queryGet;

    @Value("${tipo.get.all}")
    protected String queryGetAll;

    @Override
    public TipoResponse save(Dominio obj) throws DataAccessException, SQLException {
        try {
        	String code = Utils.createCode(obj.getDescrizione());
            jdbcUtil.update(querySave,new Object[] { code , obj.getDescrizione()});
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAll();
    }

    @Override
    public TipoResponse update(Dominio obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TipoResponse get(Dominio obj) throws DataAccessException, SQLException {
        return null;
    }

    private List<Dominio> getList() throws SQLException {
        List<Dominio> list = jdbcUtil.query(queryGetAll , rm);
        return list;
    }

    @Override
    public TipoResponse delete(Dominio obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryDelete,new Object[] { obj.getId()});
        return getAll();
    }

	@Override
	public TipoResponse getAll() throws DataAccessException, SQLException {
		TipoResponse response = new TipoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        List<Dominio> list = getList();
        response.setList(list);
        return response;
	}
    
}
