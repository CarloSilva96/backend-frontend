package com.bento.backend.domain.DTO;

import com.bento.backend.domain.Supermercado;
import com.bento.backend.service.exceptions.validations.SupermercadoCreate;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@SupermercadoCreate
public class SupermercadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Nome obrigatório.")
    @Size(min = 4, message = "Nome deve conter no mínimo 4 caracteres.")
    private String nome;

    @NotEmpty(message = "Endereço obrigatório.")
    @Size(min = 10, message = "Endereço deve conter no mínimo 10 caracteres.")
    private String endereco;

    @NotEmpty(message = "Telefone obrigatório.")
    @Size(min = 10, max = 11, message = "Tamanho do telefone inválido.")
    private String telefone;

    public SupermercadoDTO() {}

    public SupermercadoDTO(Supermercado supermercado) {
        id = supermercado.getId();
        nome = supermercado.getNome();
        endereco = supermercado.getEndereco();
        telefone = supermercado.getTelefone();
    }
}
