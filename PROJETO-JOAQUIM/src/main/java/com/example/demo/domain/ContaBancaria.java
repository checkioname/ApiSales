package com.example.demo.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.NacionalidadeDTO;

@Document(collection="conta")
public class ContaBancaria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String nomeTitular;
	private String saldo;
	private String nroAgencia;
	private NacionalidadeDTO nacionalidade;
	
	public ContaBancaria() {
		
	}

	public ContaBancaria(String id, String nomeTitular, String saldo, String nroAgencia, NacionalidadeDTO nacionalidade) {
		super();
		this.id = id;
		this.nomeTitular = nomeTitular;
		this.saldo = saldo;
		this.nroAgencia = nroAgencia;
		this.nacionalidade = nacionalidade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getNroAgencia() {
		return nroAgencia;
	}

	public void setNroAgencia(String nroAgencia) {
		this.nroAgencia = nroAgencia;
	}
	
	public NacionalidadeDTO getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(NacionalidadeDTO nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaBancaria other = (ContaBancaria) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
