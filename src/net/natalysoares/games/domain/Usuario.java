package net.natalysoares.games.domain;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.SessionScoped;

@SessionScoped
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Date dataLogin;
	
	public boolean isLogado() {
		return nome != null;
	}
	
	//===========GETTERS/SETTERS==========//
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataLogin() {
		return dataLogin;
	}
	
	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}
}