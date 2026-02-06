package com.example.Aula1.ProdutoController;


import com.example.Aula1.Produtos.FiltroRequest;
import com.example.Aula1.Produtos.ProdutoDTO;
import com.example.Aula1.Produtos.ProdutoRecord;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    @GetMapping
    public String listar() {
        return "Listando todos os produtos";
    }


    // 200 ok
    @GetMapping("/{id}") // para capturar na url produtos/id
    public String getById(@PathVariable Integer id) { //pathVariable esta chamando um parametro porque a url se chama id entao esta indicando que o id e um integer
        return "Path Variable " + id;
    }


    // ================================================================================== //

    // Inserir um recurso
    // 201 CREATED criaçao dos produtos
    // http://localhost:8080/produtos

//    @PostMapping
//    public String salvarProduto(@RequestBody ProdutoDTO produtoDTO) { // capturando apenas um objeto
//        //Jeckson
//        // "id" : "123"
//        //"nome" : "Relogio"
//        // em java sempre vai capturar so isso porque e criado manualmente como criei o ProdutoDTO com get e setter id e nome
//
//        return "Salvando o produto" + produtoDTO;
//    }

    @PostMapping
    public String salvarProdutos(@RequestBody List<ProdutoDTO> produtoDTO) { // capturando uma lista de objetos
        //Jeckson
        // "id" : "123"
        //"nome" : "Relogio"
        // em java sempre vai capturar so isso porque e criado manualmente como criei o ProdutoDTO com get e setter id e nome

        return "Salvando o produto" + produtoDTO;
    }


// produtos utilizando record
    @PostMapping("/com-record")
    public String salvarProdutoRequest(@RequestBody  List<ProdutoRecord> produto) {
        //Jeckson
        // "id" : "123"
        //"nome" : "Relogio"
        // em java sempre vai capturar so isso porque e criado manualmente como criei o ProdutoDTO com get e setter id e nome

        return "Salvando o produto" + produto;
    }

    // ================================================================================== //


    // ================================================================================== //

    // put (Atualizar um recurso)
    // 200 ok
    // 404 not found (id inexistente)
    // http://localhost:8080/produtos/1

    @PutMapping("{id}")
    public String atualizar(@PathVariable Long id, @RequestBody ProdutoRecord produtos) {
        return "Atualizando produto 1" + produtos;
    }

// ================================================================================== //


    //DELETE

//204 no Content
// 404 not found (id inexistente)
//http://localhost:8080/produtos/1


// utilizando DeleteMapping para deletar o id do api
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        return "Deletando";
    }


// ================================================================================== //


    //Filtro
    //http://localhost:8080/produtos/filtro?nome=produto 1&fabricante=Rolex
    @GetMapping("/filtro")
    public String filtro(@PathParam("nome") String nome,
                         @PathParam("fabricante") String fabricante,
                         @PathParam("data") LocalDate data) { // se for para adicionar mais filtro e so colocar , e adicionar
        return "filtrando: " + nome + " " + fabricante + " " + " === " + data;
    }


    @GetMapping("/filtro/com-record")
    public String filtro(FiltroRequest request) {
        return "Filtrando: " + request.nome() + " " + request.fabricante() + "-====" + request.data();
    }

}
