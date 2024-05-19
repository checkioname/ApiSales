package com.example.demo.config;

import java.util.Arrays;

import com.example.demo.domain.ContaBancaria;
import com.example.demo.domain.TaxaStrategy.TaxaAtmosfera;
import com.example.demo.domain.TaxaStrategy.TaxaEstelar;
import com.example.demo.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Cliente;
import com.example.demo.repository.ContaBancariaRepository;
import com.example.demo.repository.ClienteRepository;


@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ContaBancariaRepository contaRepository;

	@Override
	public void run(String... args) throws Exception {

		clienteRepository.deleteAll();
		contaRepository.deleteAll();

		Cliente cliente1 = new Cliente("Lucas", "lucas@gmail.com", 22);
		Cliente cliente2 = new Cliente("Maria", "maria@yahoo.com",21);
		Cliente cliente3 = new Cliente("Matheus", "mtheus@email.com",19);

		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2, cliente3));

		//Contas do cliente 1
		ContaBancaria conta1 = new ContaBancaria(100.000,1000.0,  "101", new ClienteDTO(cliente1.getId(),cliente1.getNome(),cliente1.getEmail(),cliente1.getIdade()),new TaxaAtmosfera());
		ContaBancaria conta2 = new ContaBancaria( 100000.00, 10000.0, "102", new ClienteDTO(cliente2.getId(),cliente2.getNome(),cliente2.getEmail(),cliente2.getIdade()),new TaxaEstelar());

		//Contas do cliente 2
		ContaBancaria conta3 = new ContaBancaria( 100.000,1000.0,  "101", new ClienteDTO(cliente1.getId(),cliente1.getNome(),cliente2.getEmail(),cliente1.getIdade()),new TaxaAtmosfera());
		ContaBancaria conta4 = new ContaBancaria( 1000.0, 10000.0, "102", new ClienteDTO(cliente2.getId(),cliente2.getNome(),cliente2.getEmail(),cliente2.getIdade()),new TaxaEstelar());


		contaRepository.saveAll(Arrays.asList(conta1, conta2));
		cliente1.getContas().addAll(Arrays.asList(conta1, conta2));

		contaRepository.saveAll(Arrays.asList(conta3, conta4));
		cliente2.getContas().addAll(Arrays.asList(conta3, conta4));

		clienteRepository.save(cliente1);
		clienteRepository.save(cliente2);
	}
}
