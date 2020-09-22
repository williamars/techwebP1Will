package br.edu.insper.model;

import java.util.Calendar;

public class Notas {
	
	private Integer id;
	private String conteudo;
	private String titulo;
	private Calendar data;
	private Integer person_id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Calendar getData() {
		return data;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public Integer getPerson_id() {
		return person_id;
	}
	
	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}

}
