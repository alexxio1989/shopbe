package com.ws.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Acquisto {

    private int id;
    private List<Prodotto> prodotti;
    private String codiceAquisto;
    private Date dataAcquisto;
    private BigDecimal totale;
    private ModalitaPagamento modalitaPagamento;
    private Date dataRitiro;
    private Negozio negozioRitiro;
    private Date dataCosegnaPrevista;
    private String stripeToken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public String getCodiceAquisto() {
        return codiceAquisto;
    }

    public void setCodiceAquisto(String codiceAquisto) {
        this.codiceAquisto = codiceAquisto;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public BigDecimal getTotale() {
        return totale;
    }

    public void setTotale(BigDecimal totale) {
        this.totale = totale;
    }

	public ModalitaPagamento getModalitaPagamento() {
		return modalitaPagamento;
	}

	public void setModalitaPagamento(ModalitaPagamento modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
	}

	public Date getDataRitiro() {
		return dataRitiro;
	}

	public void setDataRitiro(Date dataRitiro) {
		this.dataRitiro = dataRitiro;
	}

	public Negozio getNegozioRitiro() {
		return negozioRitiro;
	}

	public void setNegozioRitiro(Negozio negozioRitiro) {
		this.negozioRitiro = negozioRitiro;
	}

	public Date getDataCosegnaPrevista() {
		return dataCosegnaPrevista;
	}

	public void setDataCosegnaPrevista(Date dataCosegnaPrevista) {
		this.dataCosegnaPrevista = dataCosegnaPrevista;
	}

	public String getStripeToken() {
		return stripeToken;
	}

	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}
    
    
    
}
