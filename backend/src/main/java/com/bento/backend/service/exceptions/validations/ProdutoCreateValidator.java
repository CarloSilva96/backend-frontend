package com.bento.backend.service.exceptions.validations;

import com.bento.backend.domain.DTO.ProdutoDTO;
import com.bento.backend.resource.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ProdutoCreateValidator implements ConstraintValidator<ProdutoCreate, ProdutoDTO> {
    @Autowired
    HttpServletRequest request;

    @Override
    public void initialize(ProdutoCreate produtoCreate) {
    }

    @Override
    public boolean isValid(ProdutoDTO produtoDTO, ConstraintValidatorContext context) {
        List<FieldMessage> listaErros = new ArrayList<>();


        Boolean validCategoria = produtoDTO.getCategoria().matches("[a-zA-Z\\s]+");
        if (!validCategoria) {
            listaErros.add(new FieldMessage("categoria", "Categoria inválida"));
        }

        Boolean validNome = produtoDTO.getNome().matches("[a-zA-Z\\s]+");
        if (!validNome) {
            listaErros.add(new FieldMessage("nome", "Nome do produto não pode conter números"));
        }

        if(produtoDTO.getCodigoDeBarras() != null) {
            produtoDTO.setCodigoDeBarras(produtoDTO.getCodigoDeBarras().replaceAll("\\D", ""));
            if (produtoDTO.getCodigoDeBarras().length() != 13) {
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
