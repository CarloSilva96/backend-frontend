package com.bento.backend.domain.DTO;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SupermercadoProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID do supermercado obrigatório.")
    private Integer idSupermercado;

    @NotNull(message = "ID do produto obrigatório.")
    private Integer idProduto;

    @NotNull(message = "Obrigado informar o preço.")
    @DecimalMin(value = "0.1", message = "Valor deve ser maior de R$ 0.0")
    private Double preco;

    public SupermercadoProdutoDTO() {}

    public SupermercadoProdutoDTO(Integer idSupermercado, Integer idProduto, Double precoProduto) {
        this.idSupermercado = idSupermercado;
        this.idProduto = idProduto;
        this.preco = precoProduto;
    }
}
