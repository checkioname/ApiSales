package com.example.demo.services;

import com.example.demo.domain.ContaBancaria;
import com.example.demo.domain.TaxaStrategy.TaxaAtmosfera;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.repository.ContaBancariaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContaBancariaServiceTest {

    @Mock
    ContaBancariaRepository repoConta;
    @InjectMocks
    ContaBancariaService serviceConta;

    ContaBancaria contaAtmosfera;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        contaAtmosfera = new ContaBancaria("012093", 1000.00, 100.0, "103", new ClienteDTO("019234850945", "Lucas", "lucas@gmail", 22), new TaxaAtmosfera());

    }

    @Test
    void realizaDepositoValorPositivo() {
        when(repoConta.findById("012093")).thenReturn(Optional.of(contaAtmosfera));
        serviceConta.realizaDeposito(contaAtmosfera.getId(),100.0);
        Assertions.assertEquals(contaAtmosfera.getSaldo(),1100.00);
        Assertions.assertEquals(contaAtmosfera.getLimite(),100.0);
    }

    @Test
    void realizaDepositoValorNegativo() {
        when(repoConta.findById("012093")).thenReturn(Optional.of(contaAtmosfera));
        assertThrows(IllegalArgumentException.class,() -> {
                    serviceConta.realizaDeposito(contaAtmosfera.getId(),-100.0);
                }
        );
        Assertions.assertEquals(contaAtmosfera.getSaldo(),1000.00);
        Assertions.assertEquals(contaAtmosfera.getLimite(),100.0);
    }


    @Test
    public void testRealizaSaqueAcimaDoSaldo() {
        when(repoConta.findById("012093")).thenReturn(Optional.of(contaAtmosfera));
        Double taxa = serviceConta.realizaSaque(contaAtmosfera.getId(),1050.0);
        Assertions.assertEquals(contaAtmosfera.getSaldo(),0);
        Assertions.assertEquals(contaAtmosfera.getLimite(),50);
    }

    @Test
    public void realizaSaqueAcimaDoLimite() {
        when(repoConta.findById("012093")).thenReturn(Optional.of(contaAtmosfera));
        assertThrows(IllegalArgumentException.class,() -> {
            serviceConta.realizaSaque(contaAtmosfera.getId(), 1200.0);
                }
        );
        Assertions.assertEquals(contaAtmosfera.getSaldo(),1000.00,"O saldo foi alterado indevidamente");
        Assertions.assertEquals(contaAtmosfera.getLimite(),100.0, "O limite foi alterado indevidamente");
    }

    @Test
    public void testSaqueNegativo(){
        when(repoConta.findById("012093")).thenReturn(Optional.of(contaAtmosfera));
        assertThrows(IllegalArgumentException.class,() -> {
                    serviceConta.realizaSaque(contaAtmosfera.getId(), -12.0);
                }
        );
        Assertions.assertEquals(contaAtmosfera.getSaldo(),1000.00,"O saldo foi alterado indevidamente");
        Assertions.assertEquals(contaAtmosfera.getLimite(),100.0, "O limite foi alterado indevidamente");
    }


}
