package it.unisa.model;

import java.io.Serializable;

import java.sql.Date;

public class OrdineBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codice;
	private String metodo_di_pagamento;
	private Date data_acquisto;
	private String indirizzo_di_consegna;
	private String cliente;
	private double prezzo_totale;
	
	public OrdineBean()
	{
		codice="";
		metodo_di_pagamento="";
		data_acquisto=null;
		indirizzo_di_consegna="";
		cliente="";
		prezzo_totale=0;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getMetodo_di_pagamento() {
		return metodo_di_pagamento;
	}

	public void setMetodo_di_pagamento(String metodo_di_pagamento) {
		this.metodo_di_pagamento = metodo_di_pagamento;
	}

	public Date getData_acquisto() {
		return data_acquisto;
	}

	public void setData_acquisto(Date data_acquisto) {
		this.data_acquisto = data_acquisto;
	}

	public String getIndirizzo_di_consegna() {
		return indirizzo_di_consegna;
	}

	public void setIndirizzo_di_consegna(String indirizzo_di_consegna) {
		this.indirizzo_di_consegna = indirizzo_di_consegna;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getPrezzo_totale() {
		return prezzo_totale;
	}

	public void setPrezzo_totale(double prezzo_totale) {
		this.prezzo_totale = prezzo_totale;
	}
	
	
}
