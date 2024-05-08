package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.demo.domain.ContaBancaria;
import com.example.demo.domain.Pais;
import com.example.demo.dto.PaisDTO;
import com.example.demo.services.PaisService;

@RestController
@RequestMapping(value="/paises")
public class PaisController {
	
	@Autowired
	private PaisService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PaisDTO>> findAll(){
		List<Pais> list = service.findAll();
		
		//converte cada objeto da lista original para um DTO
		List<PaisDTO> listDto = list.stream().map(x -> new PaisDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<PaisDTO> findById(@PathVariable String id){
		Pais pais = service.findById(id);
		return ResponseEntity.ok().body(new PaisDTO(pais));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody PaisDTO pais){
		Pais obj = service.fromDTO(pais);
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
	public ResponseEntity<Void> update(@RequestBody PaisDTO objDto, @PathVariable String id){
		Pais pais = service.fromDTO(objDto);
		pais.setId(id);
		pais = service.update(pais);
		return ResponseEntity.noContent().build();
	}
	
	//retorna as contas de um país
	@GetMapping(value="/{id}/contas")
	public ResponseEntity<List<ContaBancaria>> findContas(@PathVariable String id){
		Pais pais = service.findById(id);
		return ResponseEntity.ok().body(pais.getContas());
	}
}
