package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="cliente")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String nome;
	private String email;
	@Getter @Setter	private Integer idade;
	
	@DBRef(lazy = true)
	private List<ContaBancaria> contas = new ArrayList<>();

	public Cliente(String nome, String email, Integer idade) {
		this.nome = nome;
		this.email = email;
		this.idade = idade;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<ContaBancaria> getContas() {
		return contas;
	}

	public void setContas(List<ContaBancaria> contas) {
		this.contas = contas;
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
		Cliente other = (Cliente) obj;
		return id == other.id;
	}
}
