package com.bento.backend.service.exceptions;

public class DataIntegrityException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String msgErro){
        super(msgErro);
    }
}
