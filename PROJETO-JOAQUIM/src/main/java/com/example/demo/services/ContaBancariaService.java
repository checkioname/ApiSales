package com.example.demo.services;

import java.util.List;
import java.util.Optional;

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

}
