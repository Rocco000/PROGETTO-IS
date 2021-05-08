package it.unisa.model;

import java.io.Serializable;

public class AmministratoriBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String nome;
	private String cognome;
	
	public AmministratoriBean() {
		email="";
		nome="";
		cognome="";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}
