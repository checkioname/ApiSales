package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Cliente;
import com.example.demo.repository.ClienteRepository;
import lombok.*;


@Builder
@NoArgsConstructor
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Getter private String id;
	@Getter @Setter	private String nome;
	@Getter @Setter private String email;
	@Getter @Setter private Integer idade;


	public ClienteDTO(String nome, String email, Integer idade) {
		this.nome = nome;
		this.email = email;
		this.idade = idade;
	}

	public ClienteDTO(String id, String nome, String email, Integer idade) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.idade = idade;
	}
}
