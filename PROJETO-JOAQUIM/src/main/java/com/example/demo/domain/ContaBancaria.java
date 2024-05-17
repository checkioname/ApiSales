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
	@Getter
	private String id;
	@Getter
	@Setter
	private Double saldo;
	@Getter
	@Setter
	private Double limite;
	@Getter
	@Setter
	private String nroAgencia;
	@Getter
	@Setter
	private ClienteDTO cliente;

	public ContaBancaria(ClienteDTO cliente, String nroAgencia) {
		super();
		this.saldo = 0.0;
		this.limite = 0.0;
		this.nroAgencia = nroAgencia;
		this.cliente = cliente;
	}

	public ContaBancaria(double saldo, double limite, String nroAgencia, ClienteDTO clienteDTO) {
		super();
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

	public boolean verificaSaldoSaque(double valorSaque) {
		if (valorSaque <= 0){
			return false;
		}
		if (valorSaque <= (this.saldo + this.limite)) {
				return true;
			} else {
				return false;
			}
		}

	public void sacar(double valor) {
		if (verificaSaldoSaque(valor)) {
			this.saldo -= valor;
			if (this.saldo < 0) {
				this.limite += saldo;
				this.saldo = 0.0;
			}
		}
	}
}
