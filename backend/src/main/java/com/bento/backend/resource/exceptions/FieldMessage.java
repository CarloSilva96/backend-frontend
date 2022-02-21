package com.bento.backend.resource.exceptions;
import lombok.Data;

import java.io.Serializable;

@Data
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeCampo;
    private String mensagem;

    public FieldMessage(){}

    public FieldMessage(String nomeCampo, String mensagem) {
        this.nomeCampo = nomeCampo;
        this.mensagem = mensagem;
    }
}
