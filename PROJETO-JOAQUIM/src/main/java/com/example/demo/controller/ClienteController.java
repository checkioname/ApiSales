package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.ContaBancaria;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = service.findAll();
		
		//converte cada objeto da lista original para um DTO
		List<ClienteDTO> listDto = list.stream()
				.map(x -> new ClienteDTO(x.getId(), x.getNome(), x.getEmail(), x.getIdade()))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable String id){
		Cliente cliente = service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(cliente.getId(),cliente.getNome(),cliente.getEmail(), cliente.getIdade()));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody @Valid ClienteDTO cliente){
		Cliente obj = service.fromDTO(cliente);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){
	service.delete(id);
	return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid ClienteDTO objDto, @PathVariable String id){
		Cliente cliente = service.fromDTO(objDto);
		cliente.setId(id);
		cliente = service.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	//retorna as contas de um país
	@GetMapping(value="/{id}/contas")
	public ResponseEntity<List<ContaBancaria>> findContas(@PathVariable String id){
		Cliente cliente = service.findById(id);
		return ResponseEntity.ok().body(cliente.getContas());
	}
}
