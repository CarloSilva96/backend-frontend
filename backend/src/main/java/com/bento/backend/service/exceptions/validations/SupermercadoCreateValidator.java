package com.bento.backend.service.exceptions.validations;

import com.bento.backend.domain.DTO.SupermercadoDTO;
import com.bento.backend.resource.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class SupermercadoCreateValidator implements ConstraintValidator<SupermercadoCreate, SupermercadoDTO> {
    @Autowired
    HttpServletRequest request;

    @Override
    public void initialize(SupermercadoCreate supermercadoCreate) {
    }

    @Override
    public boolean isValid(SupermercadoDTO supermercadoDTO, ConstraintValidatorContext context) {
        List<FieldMessage> listaErros = new ArrayList<>();

        Boolean validNome = supermercadoDTO.getNome().matches("[a-zA-Z\\s]+");
        if (!validNome) {
            listaErros.add(new FieldMessage("nome", "Nome do supermercado não pode conter números"));
        }

        if (supermercadoDTO.getTelefone() != null) {
            supermercadoDTO.setTelefone(supermercadoDTO.getTelefone().replaceAll("\\D", ""));
            if (supermercadoDTO.getTelefone().length() < 10 || supermercadoDTO.getTelefone().length() > 11) {
                listaErros.add(new FieldMessage("telefone", "Telefone inválido"));
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
