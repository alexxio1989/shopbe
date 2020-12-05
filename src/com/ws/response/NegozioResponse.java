package com.ws.response;

import java.util.List;

import com.ws.models.Negozio;
import com.ws.response.core.ResponseCore;

public class NegozioResponse extends ResponseCore{

    private List<Negozio> list;

    private Negozio negozio;

    public List<Negozio> getList() {
        return list;
    }

    public void setList(List<Negozio> list) {
        this.list = list;
    }

    public Negozio getNegozio() {
        return negozio;
    }

    public void setNegozio(Negozio negozio) {
        this.negozio = negozio;
    }
}
