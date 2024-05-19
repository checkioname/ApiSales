package com.example.demo.config.validacao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class FormArgumentErrorDTO {
    @Getter @Setter private String field;
    @Getter @Setter private String error;


}


