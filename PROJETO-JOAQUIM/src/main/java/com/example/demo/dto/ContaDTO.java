package com.example.demo.dto;

import java.io.Serializable;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	@Setter
	private @Getter String nome;
	private @Getter String agencia;

}