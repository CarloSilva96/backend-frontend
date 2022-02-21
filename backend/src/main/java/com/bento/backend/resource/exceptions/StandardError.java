package com.bento.backend.resource.exceptions;

import lombok.Data;

import java.io.Serializable;

@Data
public class StandardError implements Serializable {
    private Integer status;
    private String msg;

    public StandardError() {
    }

    public StandardError(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
