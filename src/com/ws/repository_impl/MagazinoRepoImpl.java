package com.ws.repository_impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ws.models.Dominio;
import com.ws.models.Magazino;
import com.ws.models.Prodotto;
import com.ws.models.SubDominio;
import com.ws.repository.IMagazinoRepo;
import com.ws.rowmapper.MagazinoRowMapper;
import com.ws.rowmapper.ProdottoRowMapper;
import com.ws.rowmapper.SottoTipoRowMapperLite;
import com.ws.rowmapper.TipoRowMapperLite;
import com.ws.utils.JdbcUtil;

@Repository
public class MagazinoRepoImpl implements IMagazinoRepo{

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private MagazinoRowMapper rm;
    
    @Autowired
    private TipoRowMapperLite tipoRowMapper;
    
    @Autowired
    private SottoTipoRowMapperLite sottoTipoRowMapper;
    
    @Autowired
    private ProdottoRowMapper prodottoRowMapper;

    @Value("${magazino.save}")
    protected String querySave;

    @Value("${magazino.delete}")
    protected String queryDelete;

    @Value("${magazino.update}")
    protected String queryUpdate;

    @Value("${magazino.get}")
    protected String queryGet;
    
    @Value("${get.tipi.sottotipi.prodotti}")
    protected String getTipiSottoTipiEProdotti;

    @Override
    public Magazino save(Magazino obj) throws DataAccessException, SQLException {
        jdbcUtil.update(querySave, new Object[] {obj.getProdottoSelected().getId() , obj.getIdNegozio() , obj.getProdottoSelected().getQntRimanente()});
        return get(obj);
    }

    @Override
    public Magazino update(Magazino obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryUpdate, new Object[] {obj.getProdottoSelected().getQntRimanente() , obj.getProdottoSelected().getId() , obj.getIdNegozio()});
        return get(obj);
    }

    @Override
    public Magazino get(Magazino obj) throws DataAccessException, SQLException {
    	
    	 Magazino newMagazino = new Magazino();
    	
    	 List<Dominio> listDominio = jdbcUtil.query(getTipiSottoTipiEProdotti, new Object[]{obj.getIdNegozio()} , tipoRowMapper);
    	 
    	 List<SubDominio> listSubDominio = jdbcUtil.query(getTipiSottoTipiEProdotti, new Object[]{obj.getIdNegozio()} , sottoTipoRowMapper);
    	 
    	 List<Prodotto> listProdotto = jdbcUtil.query(getTipiSottoTipiEProdotti, new Object[]{obj.getIdNegozio()} , prodottoRowMapper);
    	 
    	 List<Dominio> listDominioNew = new ArrayList<Dominio>();
    	 
    	 List<SubDominio> listSubDominioNew = new ArrayList<SubDominio>();
    	 
    	 for (Dominio tipo : listDominio) {
    		if(!listDominioNew.stream().filter(t -> t.getId() == tipo.getId()).findFirst().isPresent()) {
    			listDominioNew.add(tipo);
    			
    		}
		 }
    	 
    	 for (SubDominio subDominio : listSubDominio) {
    		 if(!listSubDominioNew.stream().filter(t -> t.getId() == subDominio.getId()).findFirst().isPresent()) {
    			 listSubDominioNew.add(subDominio);
     			
     		}
		}
    	 for (Dominio tipo : listDominioNew) {
    		 if(listDominioNew.size() > 0) {
    			 for (SubDominio sottoTipo : listSubDominioNew) {
    				 if(sottoTipo.getIdPadre() == tipo.getId()) {
    					 for (Prodotto prodotto : listProdotto) {
    						 if(sottoTipo.getId() == prodotto.getTipo().getId()) {
    							 sottoTipo.getProdottiAssociati().add(prodotto);
    						 }
    					 }
    					 tipo.getSottoTipi().add(sottoTipo);
    				 }
    			 }
    			 
    		 }
    		 
    	 }
    	
    	 newMagazino.setTipiAssociati(listDominioNew);
    	 newMagazino.setIdNegozio(obj.getIdNegozio());
        return newMagazino;
    }

    @Override
    public Magazino delete(Magazino obj) throws DataAccessException, SQLException {
        jdbcUtil.update(queryDelete, new Object[] {obj.getProdottoSelected().getId() , obj.getIdNegozio() });
        return get(obj);
    }

	@Override
	public Magazino getAll() throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
