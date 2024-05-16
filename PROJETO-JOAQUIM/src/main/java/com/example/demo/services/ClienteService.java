package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public ClienteService(ClienteRepository repository) {
		this.repo = repository;
	}

	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Cliente findById(String id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("País não encontrado"));
	}
	
	public Cliente insert(Cliente obj) {
		return repo.insert(obj);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getNome(), objDto.getEmail(), objDto.getIdade());
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newCliente = findById(obj.getId());
		updateData(newCliente, obj);
		return repo.save(newCliente);
	}
	
	public void updateData(Cliente newCliente, Cliente obj) {
		newCliente.setNome(obj.getNome());
		newCliente.setEmail(obj.getEmail());
	}
}
