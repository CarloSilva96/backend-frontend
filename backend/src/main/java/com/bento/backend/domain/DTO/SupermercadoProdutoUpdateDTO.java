package com.bento.backend.domain.DTO;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SupermercadoProdutoUpdateDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Obrigado informar o preço.")
    @DecimalMin(value = "0.1", message = "Valor deve ser maior de R$ 0.0")
    private Double preco;

    public SupermercadoProdutoUpdateDTO() {}

    public SupermercadoProdutoUpdateDTO(Double precoProduto) {
        this.preco = precoProduto;
    }
}
