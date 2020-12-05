package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ws.models.Dominio;
import com.ws.repository.ITipoRepo;
import com.ws.response.TipoResponse;
import com.ws.rowmapper.TipoRowMapper;
import com.ws.utils.JdbcUtil;
import com.ws.utils.Utils;

@Repository
public class TipoRepoImpl implements ITipoRepo<Dominio,TipoResponse> {

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
    public ResponseEntity<TipoResponse> save(Dominio obj) throws DataAccessException, SQLException {
        try {
            jdbcUtil.update(querySave,new Object[] { obj.getCodice() , obj.getDescrizione()});
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get(obj);
    }

    @Override
    public ResponseEntity<TipoResponse> update(Dominio obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<TipoResponse> get(Dominio obj) throws DataAccessException, SQLException {
        TipoResponse response = new TipoResponse();
        List<Dominio> list = getList();
        response.setList(list);
        return Utils.getResponseEntity(response, HttpStatus.OK);
    }

    private List<Dominio> getList() throws SQLException {
        List<Dominio> list = jdbcUtil.query(queryGetAll , rm);
        return list;
    }

    @Override
    public ResponseEntity<TipoResponse> delete(Dominio obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryDelete,new Object[] { obj.getId()});
        return get(obj);
    }
    
}
