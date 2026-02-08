package com.example.Aula1.ProdutoController;


import com.example.Aula1.Model.Produto;
import com.example.Aula1.Produtos.FiltroRequest;
import com.example.Aula1.Produtos.ProdutoDTO;
import com.example.Aula1.Produtos.ProdutoRecord;
import com.example.Aula1.service.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@Validated@RequiredArgsConstructor
public class ProdutoController {

// e era utilizado quando nao existia o @RequiredArgsConstructor isso serve para ajudar a nao colocar uns 10 ou 20 @Autowired para teste
//@Autowired // e isso força para que o teste funcione

private ProdutoService produtoService; // no private porque nao tem como fazer um teste unitario se esta funcionando ou n

//    @GetMapping
//    public ResponseEntity<Map< String,String>> listar() {
//        return ResponseEntity.status(HttpStatus.LOCKED).body(List.of("Lucas" , "Joao"));
//    }

//    @GetMapping
//    public ResponseEntity<List<String>> listar() {
//        return ResponseEntity.status(HttpStatus.LOCKED).body(List.of("Lucas" , "Joao"));
//    }

    @GetMapping
    public ResponseEntity<String> listar(List<String> produtos) {
        return  ResponseEntity.ok("Listando todos os produtos" + produtos);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> lista = new ProdutoService().listar();
        return  ResponseEntity.ok(produtoService.listar());
    }



    // 200 ok
    // 404 not found (id inexistente)
    //http://localhost:8080/produtos
    //http://localhost:8080/produtos/1
//    @GetMapping("/{id}") // para capturar na url produtos/id
//    public ResponseEntity<String> getById(@Positive @PathVariable Long id) { //pathVariable esta chamando um parametro porque a url se chama id entao esta indicando que o id e um integer
//        produtoService.findById(id);
//        return ResponseEntity.ok("Path Variable " + id);
//    }


    // ERRADO
//    @GetMapping("/{id}") // para capturar na url produtos/id
//    public ResponseEntity<Produto> getById(@Positive @PathVariable Long id) { //pathVariable esta chamando um parametro porque a url se chama id entao esta indicando que o id e um integer
//        Optional<Produto> byId =  produtoService.findById(id);
//        return ResponseEntity.ok(byId.get());
//    }

     @GetMapping("/{id}") // para capturar na url produtos/id
    public ResponseEntity<?> getById(@Positive @PathVariable Long id) { //pathVariable esta chamando um parametro porque a url se chama id entao esta indicando que o id e um integer;
//        vai para avaliaçao nao podera ser utilizado if e else
       return  produtoService.findById(id).map(produto -> ResponseEntity.ok(produto)).orElseGet(() -> ResponseEntity.notFound().build());
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
    public String salvarProdutos(@RequestBody ProdutoDTO produtoDTO) { // capturando uma lista de objetos
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
    public String filtro(@Valid FiltroRequest request) {
        return "Filtrando: " + request.nome() + " " + request.fabricante() + "-====" + request.data();
    }

}
