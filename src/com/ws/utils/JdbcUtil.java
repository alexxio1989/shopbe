package com.ws.utils;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.sql.Statement;

@Component
public class JdbcUtil {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public int update(String sql, Object... args) throws DataAccessException, SQLException {
		int intValue = jdbcTemplate.update(sql, args);
		jdbcTemplate.getDataSource().getConnection().close();
		return intValue;
	}
	
	public int update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder) throws SQLException {
		int intValue = jdbcTemplate.update(psc, generatedKeyHolder);
		jdbcTemplate.getDataSource().getConnection().close();
		return intValue;
	}
	
	public <T> T queryForObject(String sql, Object[] args, Class<T> requiredType) throws DataAccessException, SQLException {
		T objReturn = jdbcTemplate.queryForObject(sql, args, requiredType);
		jdbcTemplate.getDataSource().getConnection().close();
		return objReturn;
	}
	
	public <T> T queryForObj(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException, SQLException{
		T objReturn = jdbcTemplate.queryForObject(sql, args, rowMapper);
		jdbcTemplate.getDataSource().getConnection().close();
		return objReturn;
	}
	
	public Integer queryForInteger(String sql, Object[] args) throws DataAccessException, SQLException{
		Integer objReturn = jdbcTemplate.queryForObject(sql, args, Integer.class);
		jdbcTemplate.getDataSource().getConnection().close();
		return objReturn;
	}
	
	public <T> List<T> query(String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper) throws DataAccessException, SQLException{
		List<T> list = jdbcTemplate.query(sql, args, argTypes, rowMapper);
		jdbcTemplate.getDataSource().getConnection().close();
		return list;
	}
	
	public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException, SQLException{
		List<T> list = jdbcTemplate.query(sql, args, rowMapper);
		jdbcTemplate.getDataSource().getConnection().close();
		return list;
	}

	public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws DataAccessException, SQLException{
		List<T> list = jdbcTemplate.query(sql, rowMapper);
		jdbcTemplate.getDataSource().getConnection().close();
		return list;
	}

	public int saveAndGetId(Object[] objs , String query) throws SQLException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		AtomicInteger count = new AtomicInteger();
        update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			for (Object object : objs) {
				if( object instanceof String ) {
					ps.setString(count.intValue() + 1, String.valueOf(object));
				}
				if( object instanceof Integer ) {
					ps.setInt(count.intValue() + 1, (Integer) object);
				}
				if( object instanceof BigDecimal ) {
					ps.setBigDecimal(count.intValue() + 1, (BigDecimal) object);
				}
				count.incrementAndGet();
			}
            return ps;
        }, keyHolder);
        return  keyHolder.getKey().intValue();
	}

}
