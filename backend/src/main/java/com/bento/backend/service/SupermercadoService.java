package com.bento.backend.service;

import com.bento.backend.domain.DTO.*;
import com.bento.backend.domain.Produto;
import com.bento.backend.domain.Supermercado;
import com.bento.backend.domain.SupermercadoProduto;
import com.bento.backend.repository.SupermercadoProdutoRepository;
import com.bento.backend.repository.SupermercadoRepository;
import com.bento.backend.service.exceptions.DataIntegrityException;
import com.bento.backend.service.exceptions.ObjectNotFoundException;
import com.bento.backend.service.exceptions.ProdutoVinculadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupermercadoService {

    @Autowired
    private SupermercadoRepository supermercadoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private SupermercadoProdutoRepository supermercadoProdutoRepository;

    public Supermercado findId(Integer id) {
        Optional<Supermercado> supermercado = supermercadoRepository.findById(id);
        return supermercado.orElseThrow(() -> new ObjectNotFoundException(Supermercado.class.getName() + " não encontrado! Id: " + id));
    }

    public SupermercadoProduto findProduto(Integer idSupermercado, Integer idProduto) {
        findId(idSupermercado);
        produtoService.findId(idProduto);
        Optional<SupermercadoProduto> supermercadoProduto = supermercadoProdutoRepository.findProdutoSupermercado(idSupermercado, idProduto);
        return supermercadoProduto.orElseThrow(() -> new ObjectNotFoundException("Produto inexistente."));
    }

    @Transactional
    public SupermercadoDTO insert(Supermercado supermercado) {
        supermercado.setId(null);
        try {
            supermercado = supermercadoRepository.save(supermercado);
            SupermercadoDTO supermercadoDTO = new SupermercadoDTO(supermercado);
            return supermercadoDTO;
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(e.getMostSpecificCause().getMessage());
        }
    }

    @Transactional
    public SupermercadoDTO update(Supermercado supermercadoUpdateDTO){
        Supermercado supermercado = findId(supermercadoUpdateDTO.getId());
        updateDadosSupermercado(supermercado, supermercadoUpdateDTO);
        supermercado = supermercadoRepository.save(supermercado);
        SupermercadoDTO superDTO = new SupermercadoDTO(supermercado);
        return superDTO;
    }

    @Transactional
    public void delete(Integer id) {
        findId(id);
        supermercadoRepository.deleteById(id);
    }

    public List<SupermercadoDTO> findAllSupermercado(String ordenar) {
        if (ordenar.equals("nome")) {
            return supermercadoRepository.findAllSupermercadosPorNome();
        }
        else {
            return supermercadoRepository.findAllSupermercadosS();
        }
    }

    @Transactional
    public SupermercadoProduto insertProduto(SupermercadoProdutoDTO supermercadoProdutoDTO) {
        Supermercado supermercado = findId(supermercadoProdutoDTO.getIdSupermercado());
        Produto produto = produtoService.findId(supermercadoProdutoDTO.getIdProduto());
        SupermercadoProduto supermercadoProduto = new SupermercadoProduto();
        try {
            supermercadoProduto.setProduto(produto);
            supermercadoProduto.setSupermercado(supermercado);
            supermercadoProduto.setPrecoProduto(supermercadoProdutoDTO.getPreco());
            produtoVinculadoSupermercado(supermercado.getProdutos(), supermercadoProduto);
            return supermercadoProdutoRepository.save(supermercadoProduto);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(e.getMostSpecificCause().getMessage());
        }
    }

    @Transactional
    public SupermercadoProduto updateProdutoSupermercado(Integer idSupermercado, Integer idProduto, SupermercadoProdutoUpdateDTO spuDTO) {
        try {
            SupermercadoProduto supermercadoProduto = findProduto(idSupermercado, idProduto);
            updateDadosSupermercadoProduto(supermercadoProduto, spuDTO);
            return supermercadoProdutoRepository.save(supermercadoProduto);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(e.getMostSpecificCause().getMessage());
        }
    }

    public List<SupermercadoProdutoViewDTO> findAllProdutosSupermercado(Integer id, String ordenar) {
        findId(id);
        List<SupermercadoProdutoViewDTO> produtosCadastrados = new ArrayList<>();
        if (ordenar.equals("nome")) {
            produtosCadastrados = supermercadoProdutoRepository.findAllProdutosOrderByNome(id);
        }
        else {
            produtosCadastrados = supermercadoProdutoRepository.findAllProdutos(id);
        }
        return produtosCadastrados;
    }

    public void produtoVinculadoSupermercado(List<SupermercadoProduto> supermercadoProdutos, SupermercadoProduto supermercadoProduto) {
        for (SupermercadoProduto sp : supermercadoProdutos) {
            if (sp.equals(supermercadoProduto)) {
                throw new ProdutoVinculadoException("Produto já cadastrado no supermercado!");
            }
        }
    }

    public Supermercado converterParaSupermercado(SupermercadoDTO supermercadoDTO) {
        return new Supermercado(supermercadoDTO.getNome(),
                supermercadoDTO.getEndereco(), supermercadoDTO.getTelefone());
    }

    public Supermercado converterParaSupermercado(SupermercadoUpdateDTO supermercadoUpdateDTO) {
        return new Supermercado(supermercadoUpdateDTO.getNome(),
                supermercadoUpdateDTO.getEndereco(), supermercadoUpdateDTO.getTelefone() );
    }


    public void updateDadosSupermercado(Supermercado supermercado, Supermercado supermercadoUpdateDTO) {
        if (supermercadoUpdateDTO.getNome() != null) {
            supermercado.setNome(supermercadoUpdateDTO.getNome());
        }
        if (supermercadoUpdateDTO.getEndereco() != null) {
            supermercado.setEndereco(supermercadoUpdateDTO.getEndereco());
        }
        if (supermercadoUpdateDTO.getTelefone() != null) {
            supermercado.setTelefone(supermercadoUpdateDTO.getTelefone());
        }
    }

    public void updateDadosSupermercadoProduto(SupermercadoProduto supermercadoProduto, SupermercadoProdutoUpdateDTO supermercadoProdutoDTO) {
        if (supermercadoProdutoDTO.getPreco() != null) {
            supermercadoProduto.setPrecoProduto(supermercadoProdutoDTO.getPreco());
        }
    }
}
