package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Pais;

public class NacionalidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	
	public NacionalidadeDTO() {
		
	}
	
	public NacionalidadeDTO(Pais obj) {
		id = obj.getId();
		nome = obj.getNome();
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
	
	
}
