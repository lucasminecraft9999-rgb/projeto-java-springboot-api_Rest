package com.example.Aula1.Produtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter@Setter@ToString
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String fabricante;
//    private List<Integer> tamanhos; // se for para capturar os tamanhos dentro da lista de produtos tipo se no json tiver assim "tamanhos": [1 , 5, 200 , 3 ,1]
//    private List<SubProduto> subProdutos; // para capturar a lista dos subprodutos dentro e produtos
}
