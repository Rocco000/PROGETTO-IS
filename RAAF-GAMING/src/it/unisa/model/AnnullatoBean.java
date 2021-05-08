package it.unisa.model;

import java.io.Serializable;
import java.util.Date;

public class AnnullatoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codice;
	private Date data_di_annullamento;
	
	public AnnullatoBean() {
		codice="";
		data_di_annullamento=null;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Date getData_di_annullamento() {
		return data_di_annullamento;
	}

	public void setData_di_annullamento(Date data_di_annullamento) {
		this.data_di_annullamento = data_di_annullamento;
	}
	
	
}
