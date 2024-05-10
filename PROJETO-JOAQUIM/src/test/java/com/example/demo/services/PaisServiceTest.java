package com.example.demo.services;

import com.example.demo.repository.PaisRepository;
import com.example.demo.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.demo.domain.Pais;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PaisServiceTest {

    private PaisService service;

    @Mock
    private PaisRepository repository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.service = new PaisService(repository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
    }


    @Test
    void findById() {
        Pais pais = new Pais("123","Brasil","America do Sul","1000000");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(pais));
        Pais response = service.findById("123");
        Assertions.assertEquals(response, pais);
    }

    @Test
    void insert() {
        Pais pais = new Pais("123","Brasil","America do Sul","1000000");
        Mockito.when(repository.insert((Pais) Mockito.any())).thenReturn(pais);
        Pais response = service.insert(Mockito.any());
        Assertions.assertEquals(response,pais);
    }

    @Test
    void fromDTO() {
    }

    @Test()
    void shouldReturnExceptionWhenDeleteUserIdNull(){
        assertThrows(ObjectNotFoundException.class , ()-> service.delete(null));
    }

    @Test
    void delete() {
        repository.deleteById(anyString());
        repository.deleteById(anyString());
        verify(repository, times(2)).deleteById(anyString());
    }

    @Test
    void update() {
    }

    @Test
    void updateData() {
    }
}