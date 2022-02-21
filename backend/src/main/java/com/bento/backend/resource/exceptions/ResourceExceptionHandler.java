package com.bento.backend.resource.exceptions;

import com.bento.backend.service.exceptions.DataIntegrityException;
import com.bento.backend.service.exceptions.ObjectNotFoundException;
import com.bento.backend.service.exceptions.ParametroInexistenteException;
import com.bento.backend.service.exceptions.ProdutoVinculadoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException excecao){
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), excecao.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(ParametroInexistenteException.class)
    public ResponseEntity<StandardError> parametroInexistenteException(ParametroInexistenteException e) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(standardError.getStatus()).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException excecao){
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), excecao.getLocalizedMessage());
        for ( FieldError elemento : excecao.getBindingResult().getFieldErrors()){
            error.adicionarError(elemento.getField(), elemento.getDefaultMessage());
        }
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException excecao){
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), excecao.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(ProdutoVinculadoException.class)
    public ResponseEntity<StandardError> produtoVinculadoException(ProdutoVinculadoException excecao){
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), excecao.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> erroConversaoUrl(MethodArgumentTypeMismatchException excecao) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), excecao.getLocalizedMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> jsonParseError(HttpMessageNotReadableException excecao) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), excecao.getLocalizedMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dtViolation(DataIntegrityViolationException excecao) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), excecao.getLocalizedMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }


}
