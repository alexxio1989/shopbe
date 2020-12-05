package com.ws.response;

import java.util.List;

import com.ws.models.Prodotto;
import com.ws.response.core.ResponseCore;

public class ProdottoResponse extends ResponseCore {

    List<Prodotto> list;

    public List<Prodotto> getList() {
        return list;
    }

    public void setList(List<Prodotto> list) {
        this.list = list;
    }
    
}
