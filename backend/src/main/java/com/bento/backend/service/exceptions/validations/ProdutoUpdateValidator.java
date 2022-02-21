package com.bento.backend.service.exceptions.validations;

import com.bento.backend.domain.DTO.ProdutoUpdateDTO;
import com.bento.backend.resource.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ProdutoUpdateValidator implements ConstraintValidator<ProdutoUpdate, ProdutoUpdateDTO> {
    @Autowired
    HttpServletRequest request;

    @Override
    public void initialize(ProdutoUpdate produtoUpdate) {
    }

    @Override
    public boolean isValid(ProdutoUpdateDTO produtoUpdateDTO, ConstraintValidatorContext context) {
        List<FieldMessage> listaErros = new ArrayList<>();
        
        Boolean validCategoria = produtoUpdateDTO.getCategoria().matches("[a-zA-Z\\s]+");
        if (!validCategoria) {
            listaErros.add(new FieldMessage("categoria", "Categoria inválida"));
        }

        Boolean validNome = produtoUpdateDTO.getNome().matches("[a-zA-Z\\s]+");
        if (!validNome) {
            listaErros.add(new FieldMessage("nome", "Nome do produto não pode conter números"));
        }

        if(produtoUpdateDTO.getCodigoDeBarras() != null) {
            produtoUpdateDTO.setCodigoDeBarras(produtoUpdateDTO.getCodigoDeBarras().replaceAll("\\D", ""));
            if (produtoUpdateDTO.getCodigoDeBarras().length() != 13) {
                listaErros.add(new FieldMessage("codigoDeBarras", "Código de barras inválido"));
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
