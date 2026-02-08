package com.example.Aula1.service;

import com.example.Aula1.Model.Produto;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoService {
 final  List<Produto>  produtos = new ArrayList<>();


    public ProdutoService() {
        Produto produto1 = new Produto(1l , "Mouse gamer" , "Razer");
        Produto produto2 = new Produto(2l , "gtr" , "gta");
        Produto produto3 = new Produto(3l , "mine" , "chocholhos");
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto3);

    }
    public List<Produto> listar(){
        return List.copyOf(produtos);
    }

    public Optional<Produto> findById(@Positive Long id) {
        Optional<Produto> first = produtos.stream().filter(p -> p.getId().equals(id))
                .findFirst();

        return first;
    }
}
