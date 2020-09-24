package br.edu.insper.model;

public class Login {
	private int id;
	private String user;
	private String senha;
	private int logado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getLogado() {
		return logado;
	}
	public void setLogado(Integer logado) {
		this.logado = logado;
	}
	
	
}
