package com.example.Aula1.Produtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


// request funciona igual class porem nao teria como utilizar setters para criar ou ajustar dentro dela
public record FiltroRequest (
     @NotBlank
        String nome ,
     String fabricante,
     LocalDate data
){}
