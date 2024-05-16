package com.example.demo.services;

import com.example.demo.domain.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ClientServiceTest {

    private ClienteService service;

    @Mock
    private ClienteRepository repository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.service = new ClienteService(repository);
    }

    @Test
    void findAll() {
        List<Cliente> clientes = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(clientes);
        List<Cliente> response = service.findAll();
        assertEquals(response, clientes);
    }


    @Test
    @DisplayName("Testing finding user with a valid id")
    void findById() {
        Cliente pais = new Cliente("123","lucas","lucas@gmail.com");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(pais));
        Cliente response = service.findById("123");
        Assertions.assertEquals(response, pais);
    }

    @Test
    @DisplayName("Testing country insert")
    void insert() {
        Cliente pais = new Cliente("123","lucas","lucas@gmail.com");
        Mockito.when(repository.insert((Cliente) Mockito.any())).thenReturn(pais);
        Cliente response = service.insert(Mockito.any());
        Assertions.assertEquals(response,pais);
    }

    @Test()
    @DisplayName("Testing delete method with a null id")
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