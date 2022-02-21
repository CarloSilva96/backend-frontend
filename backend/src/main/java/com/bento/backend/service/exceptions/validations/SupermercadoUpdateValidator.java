package com.bento.backend.service.exceptions.validations;

import com.bento.backend.domain.DTO.SupermercadoUpdateDTO;
import com.bento.backend.resource.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class SupermercadoUpdateValidator implements ConstraintValidator<SupermercadoUpdate, SupermercadoUpdateDTO> {
    @Autowired
    HttpServletRequest request;

    @Override
    public void initialize(SupermercadoUpdate supermercadoUpdate) {
    }

    @Override
    public boolean isValid(SupermercadoUpdateDTO supermercadoUpdateDTO, ConstraintValidatorContext context) {
        List<FieldMessage> listaErros = new ArrayList<>();

        Boolean validNome = supermercadoUpdateDTO.getNome().matches("[a-zA-Z\\s]+");
        if (!validNome) {
            listaErros.add(new FieldMessage("nome", "Nome do supermercado não pode conter números"));
        }

        if (supermercadoUpdateDTO.getTelefone() != null) {
            supermercadoUpdateDTO.setTelefone(supermercadoUpdateDTO.getTelefone().replaceAll("\\D", ""));
            if (supermercadoUpdateDTO.getTelefone().length() < 10 || supermercadoUpdateDTO.getTelefone().length() > 11) {
                listaErros.add( new FieldMessage( "telefone", "Telefone inválido"));
            }
        }
        for (FieldMessage e : listaErros) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem())
                    .addPropertyNode(e.getNomeCampo()).addConstraintViolation();
        }
        return listaErros.isEmpty();
    }
}
