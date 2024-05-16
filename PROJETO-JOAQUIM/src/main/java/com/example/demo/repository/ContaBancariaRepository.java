package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.ContaBancaria;

@Repository
public interface ContaBancariaRepository extends MongoRepository<ContaBancaria, String> {
	
	List<ContaBancaria> findByNomeTitularContaining(String name);



	ContaBancaria findByNomeTitular(String name);
}
