package com.bento.backend.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "produtos")
public class Produto implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private String codigoDeBarras;

    @Column(nullable = false)
    private String categoria;

    private Double preco;

    @OneToMany(mappedBy = "id.produto", cascade = CascadeType.ALL)
    private List<SupermercadoProduto> produtos = new ArrayList<>();

    public Produto() {}

    public Produto(String nome, String codigoDeBarras, String categoria, Double preco) {
        this.nome = nome;
        this.codigoDeBarras = codigoDeBarras;
        this.categoria = categoria;
        this.preco = preco;
    }
}
