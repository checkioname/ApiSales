package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ContaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ContaBancaria;
import com.example.demo.repository.ContaBancariaRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class ContaBancariaService {
	
	@Autowired
	private ContaBancariaRepository repo;
	
	public ContaBancaria findById(String id) {
		Optional<ContaBancaria> conta = repo.findById(id);
		return conta.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada"));
	}
	
	public List<ContaBancaria> findByNomeTitular(String name) {
		return repo.findByNomeTitularContaining(name);
	}

	public ContaBancaria fromDTO(ContaDTO objDto) {
		return new ContaBancaria(objDto.getNome(), objDto.getAgencia());
	}

	public void realizaSaque(String ContaId, Double valor){
		ContaBancaria conta = findById(ContaId);
		if (conta.verificaLimite(valor)){
			conta.realizaSaque(valor);
			repo.save(conta);
		}
		else{
			throw new IllegalArgumentException("Saldo insuficiente.");
		}

	}

}
