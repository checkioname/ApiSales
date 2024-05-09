package com.example.demo.services;

import com.example.demo.repository.PaisRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.demo.domain.Pais;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    }

    @Test
    void fromDTO() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void updateData() {
    }
}