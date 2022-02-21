package com.bento.backend.resource.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> listaMensagens = new ArrayList<>();

    public ValidationError(Integer status, String msg) {
        super(status, msg);
    }

    public List<FieldMessage> getErrors() {
        return listaMensagens;
    }

    public void adicionarError(String nomeCampo, String mensagem) {
        listaMensagens.add(new FieldMessage(nomeCampo, mensagem));
    }
}
