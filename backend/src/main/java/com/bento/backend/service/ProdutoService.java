package com.bento.backend.service;

import com.bento.backend.domain.DTO.ProdutoDTO;
import com.bento.backend.domain.DTO.ProdutoUpdateDTO;
import com.bento.backend.domain.Produto;
import com.bento.backend.repository.ProdutoRepository;
import com.bento.backend.service.exceptions.DataIntegrityException;
import com.bento.backend.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findId(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException(Produto.class.getName() + " não encontrado! Id: " + id));
    }

    @Transactional
    public ProdutoDTO insert(Produto produto) {
        produto.setId(null);
        try {
            produto = produtoRepository.save(produto);
            ProdutoDTO produtoDTO = new ProdutoDTO(produto);
            return produtoDTO;
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(e.getMostSpecificCause().getMessage());
        }

    }

    @Transactional
    public ProdutoDTO update(Produto produtoUpdateDTO){
        Produto produto = findId(produtoUpdateDTO.getId());
        updateDadosProduto(produto, produtoUpdateDTO);
        produto = produtoRepository.save(produto);

        ProdutoDTO produtoDTO = new ProdutoDTO(produto);
        return produtoDTO;
    }

    @Transactional
    public void delete(Integer id) {
        findId(id);
        produtoRepository.deleteById(id);
    }

    public List<ProdutoDTO> findAllProdutos(String ordenar) {
        if (ordenar.equals("nome")) {
            return produtoRepository.findAllProdutosPorNome();
        }
        else {
            return produtoRepository.findAllProdutosP();
        }
    }

    public Produto converterParaProduto(ProdutoDTO produtoDTO) {
        return new Produto(produtoDTO.getNome(),
                produtoDTO.getCodigoDeBarras(), produtoDTO.getCategoria(), produtoDTO.getPreco());
    }

    public Produto converterParaProduto(ProdutoUpdateDTO produtoUpdateDTO) {
        return new Produto(produtoUpdateDTO.getNome(),
                produtoUpdateDTO.getCodigoDeBarras(), produtoUpdateDTO.getCategoria(), produtoUpdateDTO.getPreco());
    }

    public void updateDadosProduto(Produto produto, Produto produtoUpdateDTO) {
        if (produtoUpdateDTO.getNome() != null) {
            produto.setNome(produtoUpdateDTO.getNome());
        }
        if (produtoUpdateDTO.getCodigoDeBarras() != null) {
            produto.setCodigoDeBarras(produtoUpdateDTO.getCodigoDeBarras());
        }
        if (produtoUpdateDTO.getCategoria() != null) {
            produto.setCategoria(produtoUpdateDTO.getCategoria());
        }
        produto.setPreco(produtoUpdateDTO.getPreco());
    }
}
