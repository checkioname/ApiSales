package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Pais;
import com.example.demo.dto.PaisDTO;
import com.example.demo.repository.PaisRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class PaisService {
	
	@Autowired
	private PaisRepository repo;
	
	public List<Pais> findAll(){
		return repo.findAll();
	}
	
	public Pais findById(String id) {
		Optional<Pais> pais = repo.findById(id);
		return pais.orElseThrow(() -> new ObjectNotFoundException("País não encontrado"));
	}
	
	public Pais insert(Pais obj) {
		return repo.insert(obj);
	}
	
	public Pais fromDTO(PaisDTO objDto) {
		return new Pais(objDto.getId(), objDto.getNome(), objDto.getContinente(), objDto.getPopulacao());
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Pais update(Pais obj) {
		Pais newPais = findById(obj.getId());
		updateData(newPais, obj);
		return repo.save(newPais);
	}
	
	public void updateData(Pais newPais, Pais obj) {
		newPais.setNome(obj.getNome());
		newPais.setContinente(obj.getContinente());
		newPais.setPopulacao(obj.getPopulacao());
	}
}
