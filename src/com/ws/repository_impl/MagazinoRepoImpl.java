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
import com.ws.rowmapper.ProdottoRowMapper;
import com.ws.rowmapper.SottoTipoRowMapperLite;
import com.ws.rowmapper.TipoRowMapperLite;
import com.ws.utils.JdbcUtil;

@Repository
public class MagazinoRepoImpl implements IMagazinoRepo{

    @Autowired
    private JdbcUtil jdbcUtil;
    
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
    
    @Value("${magazino.delete.by.prodotto}")
    protected String queryDeleteByProdotto;

    @Value("${magazino.update}")
    protected String queryUpdate;

    @Value("${magazino.get}")
    protected String queryGet;
    
    @Value("${get.tipi.sottotipi.prodotti}")
    protected String getTipiSottoTipiEProdotti;

    @Override
    public Magazino save(Magazino obj){
        try {
			jdbcUtil.update(querySave, new Object[] {obj.getProdottoSelected().getId() , obj.getIdNegozio() , obj.getProdottoSelected().getQntRimanente()});
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return get(obj);
    }

    @Override
    public Magazino update(Magazino obj){
        try {
			jdbcUtil.update(queryUpdate, new Object[] {obj.getProdottoSelected().getQntRimanente() , obj.getProdottoSelected().getId() , obj.getIdNegozio()});
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return get(obj);
    }

    @Override
    public Magazino get(Magazino obj){
    	
    	 Magazino newMagazino = new Magazino();
    	
    	 List<Dominio> listDominio = null;
    	 List<Prodotto> listProdotto  = null;
    	 List<SubDominio> listSubDominio = null;
		try {
			listDominio = jdbcUtil.query(getTipiSottoTipiEProdotti, new Object[]{obj.getIdNegozio()} , tipoRowMapper);
			listSubDominio = jdbcUtil.query(getTipiSottoTipiEProdotti, new Object[]{obj.getIdNegozio()} , sottoTipoRowMapper);
			listProdotto = jdbcUtil.query(getTipiSottoTipiEProdotti, new Object[]{obj.getIdNegozio()} , prodottoRowMapper);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
	
    	 
    	 List<Dominio> listDominioNew = new ArrayList<Dominio>();
    	 
    	 List<SubDominio> listSubDominioNew = new ArrayList<SubDominio>();
    	 
    	 if(listDominio != null) {
    		 for (Dominio tipo : listDominio) {
    			 if(!listDominioNew.stream().filter(t -> t.getId() == tipo.getId()).findFirst().isPresent()) {
    				 listDominioNew.add(tipo);
    				 
    			 }
    		 }
    	 }
    	 
    	 if(listSubDominio != null) {
    		 for (SubDominio subDominio : listSubDominio) {
    			 if(!listSubDominioNew.stream().filter(t -> t.getId() == subDominio.getId()).findFirst().isPresent()) {
    				 listSubDominioNew.add(subDominio);
    				 
    			 }
    		 }
    	 }
    	 for (Dominio tipo : listDominioNew) {
    		 if(listDominioNew.size() > 0) {
    			 for (SubDominio sottoTipo : listSubDominioNew) {
    				 if(sottoTipo.getIdPadre() == tipo.getId()) {
    					 if(listProdotto != null) {
    						 for (Prodotto prodotto : listProdotto) {
    							 if(sottoTipo.getId() == prodotto.getTipo().getId()) {
    								 sottoTipo.getProdottiAssociati().add(prodotto);
    							 }
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
    public Magazino delete(Magazino obj){
        try {
			jdbcUtil.update(queryDelete, new Object[] {obj.getProdottoSelected().getId() , obj.getIdNegozio() });
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return get(obj);
    }

	@Override
	public Magazino getAll(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Prodotto obj) {
		 try {
				jdbcUtil.update(queryDeleteByProdotto, new Object[] {obj.getId() });
				return true;
			} catch (DataAccessException | SQLException e) {
				e.printStackTrace();
				return false;
			}
	        
	}

}
