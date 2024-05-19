package com.example.demo.domain.TaxaStrategy;

public class TaxaEstelar implements Taxa {

    @Override
    public Double calcularTaxa(Double valorSaque) {
        return valorSaque * 0.01;
    }
}
