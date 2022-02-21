package com.bento.backend.service.exceptions;

public class ParametroInexistenteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ParametroInexistenteException(String msgErro) {
        super(msgErro);
    }
}
