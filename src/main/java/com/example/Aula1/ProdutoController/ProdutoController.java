package com.example.Aula1.ProdutoController;

import com.example.Aula1.Model.Produto;
import com.example.Aula1.Produtos.FiltroRequest;
import com.example.Aula1.Produtos.ProdutoDTO;
import com.example.Aula1.Produtos.ProdutoRecord;
import com.example.Aula1.service.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
@Validated
@RequiredArgsConstructor
public class ProdutoController {

    // e era utilizado quando nao existia o @RequiredArgsConstructor isso serve para ajudar a nao colocar uns 10 ou 20 @Autowired para teste
    // @Autowired // e isso força para que o teste funcione
    private final ProdutoService produtoService; // no private porque nao tem como fazer um teste unitario se esta funcionando ou n

    // ================================================================================== //
    // GET ALL
    // 200 ok
    // [get] /produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    // ================================================================================== //
    // GET ALL paginado
    /*
        x-total-Count
        x-Page
        x-Size
     */
    // [get] /produtos/paginado
    @GetMapping("/paginado")
    public ResponseEntity<List<Produto>> listarPaginado(
            @RequestHeader(value = "X-Page", required = false, defaultValue = "0") int page,
            @RequestHeader(value = "X-Size", required = false, defaultValue = "2") int size) {

        return ResponseEntity.ok(produtoService.listarPaginado(page, size));
    }

    // ================================================================================== //
    // GET BY ID
    // 200 ok
    // 404 not found (id inexistente)
    // http://localhost:8080/produtos/1
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Positive @PathVariable Long id) {
        // vai para avaliação, nao podera ser utilizado if e else
        return produtoService.findById(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ================================================================================== //
    // POST - Criar recurso
    // 201 CREATED
    // http://localhost:8080/produtos
    @PostMapping
    public ResponseEntity<?> salvarProdutos(@RequestBody ProdutoDTO produtoDTO) {
        // Jeckson
        // "id" : "123"
        // "nome" : "Relogio"
        produtoService.salvar(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // produtos utilizando record
    @PostMapping("/com-record")
    public String salvarProdutoRequest(@RequestBody List<ProdutoRecord> produto) {
        return "Salvando o produto " + produto;
    }

    // ================================================================================== //
    // PUT - Atualizar recurso
    // http://localhost:8080/produtos/1
    @PutMapping("{id}")
    public String atualizar(@PathVariable Long id, @RequestBody ProdutoRecord produtos) {
        return "Atualizando produto " + id + " " + produtos;
    }

    // ================================================================================== //
    // DELETE
    // 204 no Content
    // 404 not found (id inexistente)
    // http://localhost:8080/produtos/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // ================================================================================== //
    // Headers

    @GetMapping("/headers")
    public String headers(@RequestHeader("User-Agent") String userAgent) {
        return userAgent;
    }

    @GetMapping("/todos-headers")
    public String todosHeaders(@RequestHeader Map<String, String> headers) {
        return headers.toString();
    }

    // ================================================================================== //
    // Filtro via query params
    // http://localhost:8080/produtos/filtro?nome=produto1&fabricante=Rolex&data=2024-01-01
    @GetMapping("/filtro")
    public String filtro(@RequestParam("nome") String nome,
                         @RequestParam("fabricante") String fabricante,
                         @RequestParam("data") LocalDate data) {
        return "filtrando: " + nome + " " + fabricante + " === " + data;
    }

    // Filtro usando Record
    @GetMapping("/filtro/com-record")
    public ResponseEntity<?> filtro(@Valid FiltroRequest request) {
        return ResponseEntity.ok(produtoService.filtrar(request));
    }
}
