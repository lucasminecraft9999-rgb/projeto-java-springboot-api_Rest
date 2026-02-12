package com.example.Aula1.service;

import com.example.Aula1.Model.Produto;
import com.example.Aula1.Produtos.FiltroRequest;
import com.example.Aula1.Produtos.ProdutoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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

    public void deletar(Long id) {
        Produto produto = findById(id).orElseThrow(() -> new IllegalArgumentException("Produto nao localizado"));
    }

    public Produto salvar(ProdutoDTO produtoDTO) {
        Produto novo = new Produto(getProximoId(), produtoDTO.getNome() , produtoDTO.getFabricante());
        produtos.add(novo);
        return novo;
    }


    private Long getProximoId() {

        return produtos.stream().map(Produto::getId).max(Comparator.naturalOrder()).orElse(0L) + 1;
    }

    public List<Produto> filtrar(@Valid FiltroRequest request) {
     return this.produtos.stream().filter(produto -> request.nome() == null || produto.getNome().equals(request.nome())).
             filter(produto -> request.fabricante() == null ||  produto.getFabricante().equals(request.fabricante()))
              .collect(Collectors.toList());
    }

    public  List<Produto> listarPaginado(int page , int size) {
        List<Produto> produtos = listar();
        int total = produtos.size();
        return null;
    }
}
