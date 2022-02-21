package com.bento.backend.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "supermercados")
public class Supermercado implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String endereco;

    @Column(nullable = false, unique = true)
    private String telefone;

    @OneToMany(mappedBy = "id.supermercado", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<SupermercadoProduto> produtos = new ArrayList<>();

    public Supermercado() {}

    public Supermercado(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
