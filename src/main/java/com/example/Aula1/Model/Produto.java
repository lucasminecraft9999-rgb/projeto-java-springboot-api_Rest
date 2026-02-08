package com.example.Aula1.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@AllArgsConstructor@ToString
public class Produto {
    private Long id;
    private String nome;
    private String fabricante;
}
