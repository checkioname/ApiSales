package com.example.demo.repository;

import java.util.List;

import com.example.demo.domain.ContaBancaria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends MongoRepository<ContaBancaria, String> {
	
	List<ContaBancaria> findByCliente_NomeContaining(String name);



	ContaBancaria findByCliente_Nome(String name);
}
