package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Pais;

public class PaisDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String continente;
	private String populacao;
	
	public PaisDTO() {	
	}
	
	public PaisDTO(Pais obj) {
		id = obj.getId();
		nome = obj.getNome();
		continente = obj.getContinente();
		populacao = obj.getPopulacao();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getPopulacao() {
		return populacao;
	}

	public void setPopulacao(String populacao) {
		this.populacao = populacao;
	}
	
	
}
