package com.bento.backend.service.exceptions.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProdutoCreateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProdutoCreate {
    String message() default "Erro de validação";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
