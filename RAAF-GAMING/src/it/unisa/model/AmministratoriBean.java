package it.unisa.model;

import java.io.Serializable;

public class AmministratoriBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String nome;
	private String password;
	
	public AmministratoriBean() {
		email="";
		nome="";
		password="";
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
