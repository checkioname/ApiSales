package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ContaDTO;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ContaBancaria;
import com.example.demo.repository.ContaBancariaRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class ContaBancariaService {
	
	@Autowired
	private ContaBancariaRepository repoConta;
	@Autowired
	private ClienteRepository repoClient;

	private boolean validaValorDeposito(Double valor){
		if (valor <= 0){
			return false;
		} else{
			return true;
		}
	}
	
	public ContaBancaria findById(String id) {
		Optional<ContaBancaria> conta = repoConta.findById(id);
		return conta.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada"));
	}
	
	public List<ContaBancaria> findByCliente_Nome(String name) {
		return repoConta.findByCliente_NomeContaining(name);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getNome(), objDto.getEmail(), objDto.getIdade());
	}

	public ContaBancaria insert(ContaDTO conta) {
		Cliente cliente = repoClient.findByNome(conta.getNome());
		ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getIdade());
		return new ContaBancaria(clienteDTO, conta.getAgencia());
	}

	public void realizaDeposito(String ContaId, Double valor){
			ContaBancaria conta = repoConta.findById(ContaId)
									.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada com o ID fornecido"));;
				if (validaValorDeposito(valor)){
					conta.setSaldo(conta.getSaldo() + valor);
					repoConta.save(conta);
				}
				else{
					throw new IllegalArgumentException("O valor do depósito é invalido");
				}
			}


	public void realizaSaque(String ContaId, Double valorSaque){
		ContaBancaria conta = repoConta.findById(ContaId)
				.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada com o ID fornecido"));;
		if (conta.verificaSaldoSaque(valorSaque)){
			conta.sacar(valorSaque);
			repoConta.save(conta);}
		else{
			if (valorSaque <= 0){
				throw new IllegalArgumentException("Valor inválido");
			}else{
				throw new IllegalArgumentException("Saldo insuficiente.");
			}
		}
	}

}
