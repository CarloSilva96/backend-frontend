package com.bento.backend.domain.DTO;

import com.bento.backend.domain.SupermercadoProduto;
import lombok.Data;

import java.io.Serializable;

@Data
public class SupermercadoProdutoViewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private String codigoDeBarras;
    private String categoria;
    private Double preco;

    public SupermercadoProdutoViewDTO() {}

    public SupermercadoProdutoViewDTO(SupermercadoProduto supermercadoProduto) {
        id = supermercadoProduto.getProduto().getId();
        nome = supermercadoProduto.getProduto().getNome();
        codigoDeBarras = supermercadoProduto.getProduto().getCodigoDeBarras();
        categoria = supermercadoProduto.getProduto().getCategoria();
        preco = supermercadoProduto.getPrecoProduto();
    }
    public SupermercadoProdutoViewDTO(Integer id, String nome, String codigoDeBarras, String categoria, Double preco) {
        this.id = id;
        this.nome = nome;
        this.codigoDeBarras = codigoDeBarras;
        this.categoria = categoria;
        this.preco = preco;
    }
}
