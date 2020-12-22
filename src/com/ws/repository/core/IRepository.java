package com.ws.repository.core;

public interface IRepository<I,O> {
	
	public O save(I obj) ;
    
    public O update(I obj);
 
    public O get(I obj);
    
    public O getAll();
 
    public O delete(I obj);

}
