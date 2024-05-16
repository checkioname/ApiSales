package com.example.demo.controller;

import java.util.List;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.controller.util.URL;
import com.example.demo.domain.ContaBancaria;
import com.example.demo.services.ContaBancariaService;

@RestController
@RequestMapping(value="/contas")
public class ContaBancariaController {
	
	@Autowired
	private ContaBancariaService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ContaBancaria> findById(@PathVariable String id){
		ContaBancaria conta = service.findById(id);
		return ResponseEntity.ok().body(conta);
	}
	
	@GetMapping(value="/namesearch")
	public ResponseEntity<List<ContaBancaria>> findByName(@RequestParam(value="text", defaultValue="")String name){
		name = URL.decodeParam(name);
		List<ContaBancaria> list = service.findByCliente_Nome(name);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping(value="/")
	public ResponseEntity<Void> sendMoney(@RequestParam String nome, @RequestParam Double valor){

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{contaId}/saque")
	public ResponseEntity<String> saque(@PathVariable String contaId, @RequestParam double valor) {
		try {
			service.realizaSaque(contaId, valor);
			return ResponseEntity.ok("Saque realizado com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/{contaId}/deposito")
	public ResponseEntity<String> deposito(@PathVariable String contaId, @RequestParam double valor) {
		try {
			service.realizaDeposito(contaId, valor);
			return ResponseEntity.ok("Deposito realizado com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
