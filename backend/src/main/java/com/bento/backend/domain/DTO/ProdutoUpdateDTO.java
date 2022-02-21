package com.bento.backend.domain.DTO;

import com.bento.backend.domain.Produto;
import com.bento.backend.service.exceptions.validations.ProdutoUpdate;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@ProdutoUpdate
public class ProdutoUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Size(min = 3, message = "Nome deve conter no mínimo 3 caracteres.")
    private String nome;

    @NotEmpty(message = "Código de barras obrigatório.")
    private String codigoDeBarras;

    @Size(min = 3, message = "Categoria deve conter no mínimo 3 caracteres.")
    private String categoria;

    private Double preco;

    public ProdutoUpdateDTO() {}

    public ProdutoUpdateDTO(Produto produto) {
        id = produto.getId();
        nome = produto.getNome();
        codigoDeBarras = produto.getCodigoDeBarras();
        categoria = produto.getCategoria();
        preco = produto.getPreco();
    }
}
