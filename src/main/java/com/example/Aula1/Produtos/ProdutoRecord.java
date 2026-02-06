package com.example.Aula1.Produtos;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProdutoRecord(
        @NotNull
        Long id,
         String nome,
         Double preco,
        Integer quantidade,
        String descricao
) { }
