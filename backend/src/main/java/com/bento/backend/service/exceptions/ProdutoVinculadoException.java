package com.bento.backend.service.exceptions;

public class ProdutoVinculadoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProdutoVinculadoException(String msgErro) {
        super(msgErro);
    }
}
