package com.example.demo.domain.TaxaStrategy;

public class TaxaAtmosfera implements Taxa {

    @Override
    public Double calcularTaxa(Double valorSaque) {
        return valorSaque * 0.03;
    }
}
