package com.example.demo.domain;

import java.io.Serializable;
import java.util.Objects;

import com.example.demo.dto.ClienteDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="conta")
public class ContaBancaria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter private String id;
	@Getter@Setter private String nomeTitular;
	@Getter@Setter private Double saldo;
	@Getter@Setter private Double limite;
	@Getter@Setter private String nroAgencia;
	@Getter@Setter private ClienteDTO cliente;

	public ContaBancaria(String nomeTitular, String nroAgencia) {
		super();
		this.nomeTitular = nomeTitular;
		this.saldo = saldo;
		this.limite = limite;
		this.nroAgencia = nroAgencia;
		this.cliente = cliente;
	}

	public ContaBancaria(String nomeTitular, double saldo, double limite, String nroAgencia, ClienteDTO clienteDTO) {
		super();
		this.nomeTitular = nomeTitular;
		this.saldo = saldo;
		this.limite = limite;
		this.nroAgencia = nroAgencia;
		this.cliente = clienteDTO;
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

	public boolean verificaLimite(double valor){
		if (valor > this.saldo) {
			if (valor <= (this.saldo + this.limite)) {
				return true;
			} else {
				return false;
			}
		}
		else {
			return true;
		}
	}

	public void realizaSaque(double valor){
		if (verificaLimite(valor)){
			if (valor <= this.saldo){
				this.saldo -= valor;
			}
			if (valor > this.saldo && valor<= (this.saldo + this.limite)){
				this.limite -= valor;
			}
		}
	}

}
