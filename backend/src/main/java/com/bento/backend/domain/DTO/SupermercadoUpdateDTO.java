package com.bento.backend.domain.DTO;

import com.bento.backend.domain.Supermercado;
import com.bento.backend.service.exceptions.validations.SupermercadoUpdate;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@SupermercadoUpdate
public class SupermercadoUpdateDTO {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Size(min = 4, message = "Nome deve conter no mínimo 4 caracteres.")
    private String nome;

    @Size(min = 10, message = "Endereço deve conter no mínimo 10 caracteres.")
    private String endereco;

    @Size(min = 10, max = 11, message = "Tamanho do telefone inválido.")
    private String telefone;

    public SupermercadoUpdateDTO() {}

    public SupermercadoUpdateDTO(Supermercado supermercado) {
        id = supermercado.getId();
        nome = supermercado.getNome();
        endereco = supermercado.getEndereco();
        telefone = supermercado.getTelefone();
    }
}
