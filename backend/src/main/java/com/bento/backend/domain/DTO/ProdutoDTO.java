package com.bento.backend.domain.DTO;

import com.bento.backend.domain.Produto;
import com.bento.backend.service.exceptions.validations.ProdutoCreate;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@ProdutoCreate
public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Nome obrigatório.")
    @Size(min = 3, message = "Nome deve conter no mínimo 3 caracteres.")
    private String nome;

    @NotEmpty(message = "Código de barras obrigatório.")
    private String codigoDeBarras;

    @NotEmpty(message = "Categoria obrigatória.")
    @Size(min = 3, message = "Categoria deve conter no mínimo 3 caracteres.")
    private String categoria;

    private Double preco;

    public ProdutoDTO() {}

    public ProdutoDTO(Produto produto) {
        id = produto.getId();
        nome = produto.getNome();
        codigoDeBarras = produto.getCodigoDeBarras();
        categoria = produto.getCategoria();
        preco = produto.getPreco();
    }
}
