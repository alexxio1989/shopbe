package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ws.models.Dominio;
import com.ws.models.SubDominio;
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
    
    private SottoTipoRepoImpl sottoTipoRepoImpl;

    @Override
    public TipoResponse save(Dominio obj)  {
    	TipoResponse response = null;
    	String code = Utils.createCode(obj.getDescrizione());
        try {
			jdbcUtil.update(querySave,new Object[] { code , obj.getDescrizione()});
			response = new TipoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.SAVE));
		} catch (DataAccessException | SQLException e) {
			response = new TipoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.SAVE_ERROR));
			e.printStackTrace();
		}
       
        return getAll(response);
    }

    @Override
    public TipoResponse update(Dominio obj) {
    	TipoResponse response = null;
    	String code = Utils.createCode(obj.getDescrizione());
   	 try {
            jdbcUtil.update(queryUpdate,new Object[] {code , obj.getDescrizione(), obj.getId() });
            response = new TipoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.UPDATE));
        } catch (DataAccessException | SQLException e) {
			response = new TipoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.UPDATE_ERROR));
			e.printStackTrace();
		}
       return getAll(response);
    }

    @Override
    public TipoResponse get(Dominio obj)  {
        return null;
    }

    private List<Dominio> getList() throws SQLException {
        List<Dominio> list = jdbcUtil.query(queryGetAll , rm);
        return list;
    }

    @Override
    public TipoResponse delete(Dominio obj)  {
    	TipoResponse response = null;
        try {
        	if(obj.getSottoTipi() != null && obj.getSottoTipi().size() > 0) {
        		for (SubDominio iterable_element : obj.getSottoTipi()) {
        			sottoTipoRepoImpl.deleteAll(iterable_element);
        		}
        	}
			jdbcUtil.update(queryDelete,new Object[] { obj.getId()});
			response = new TipoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.DELETE));
		} catch (DataAccessException | SQLException e) {
			response = new TipoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.DELETE_ERROR));
			e.printStackTrace();
		}
        return getAll(response);
    }

	@Override
	public TipoResponse getAll()  {
		TipoResponse response = new TipoResponse(HttpStatus.OK, EnumResponseStatus.getStatus(EnumMetodi.GET));
        return getAll(response);
	}

	private TipoResponse getAll(TipoResponse response){
		List<Dominio> list;
		try {
			list = getList();
			response.setList(list);
		} catch (SQLException e) {
			response = new TipoResponse(HttpStatus.BAD_REQUEST, EnumResponseStatus.getStatus(EnumMetodi.GET));
			e.printStackTrace();
		}
        return response;
	}
    
}
