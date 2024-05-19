package com.example.demo.domain;

import com.example.demo.domain.TaxaStrategy.Taxa;
import com.example.demo.dto.ClienteDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Document(collection="conta")
    public class ContaBancaria {

        @Id
        @Getter
        private String id;
        @Getter
        @Setter
        private Double saldo;
        @Getter
        @Setter
        private Double limite;
        @Getter
        @Setter
        private String nroAgencia;
        @Getter
        @Setter
        private ClienteDTO cliente;

        private Taxa taxa;

        public ContaBancaria(ClienteDTO cliente, String nroAgencia) {
            super();
            this.saldo = 0.0;
            this.limite = 0.0;
            this.nroAgencia = nroAgencia;
            this.cliente = cliente;
        }

        public ContaBancaria(double saldo, double limite, String nroAgencia, ClienteDTO clienteDTO, Taxa taxa) {
            super();
            this.saldo = saldo;
            this.limite = limite;
            this.nroAgencia = nroAgencia;
            this.cliente = clienteDTO;
            this.taxa = taxa;
        }


        public int hashCode() {
            return Objects.hash(id);
        }


        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ContaBancaria other = (ContaBancaria) obj;
            return Objects.equals(id, other.id);
        }

        public boolean verificaSaldoSaque(@NotNull double valorSaque) {
            if (valorSaque <= 0) {
                return false;
            }
            if ((valorSaque + taxa.calcularTaxa(valorSaque)) <= (this.saldo + this.limite)) {
                return true;
            } else {
                return false;
            }
        }

        public @NotNull Double sacar(@NotNull Double valorSaque) {
            if (verificaSaldoSaque(valorSaque)) {
                this.saldo -= valorSaque;
                valorSaque -= taxa.calcularTaxa(valorSaque);
                if (this.saldo < 0) {
                    this.limite += saldo;
                    this.saldo = 0.0;
                }
                return valorSaque;
            }
            return 0.0;
        }
    }
