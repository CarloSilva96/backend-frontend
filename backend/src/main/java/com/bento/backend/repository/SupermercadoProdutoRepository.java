package com.bento.backend.repository;

import com.bento.backend.domain.DTO.SupermercadoProdutoViewDTO;
import com.bento.backend.domain.SupermercadoProduto;
import com.bento.backend.domain.SupermercadoProdutoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupermercadoProdutoRepository extends JpaRepository<SupermercadoProduto, SupermercadoProdutoPK> {

    @Query("SELECT u FROM SupermercadoProduto u WHERE u.id.supermercado.id = ?1 AND u.id.produto.id = ?2")
    Optional<SupermercadoProduto> findProdutoSupermercado(Integer supermercadoId, Integer produtoId);

    @Query("SELECT new com.bento.backend.domain.DTO.SupermercadoProdutoViewDTO(u.id.produto.id, u.id.produto.nome, u.id.produto.codigoDeBarras, " +
            "u.id.produto.categoria, u.precoProduto) " +
            "FROM SupermercadoProduto AS u WHERE u.id.supermercado.id = ?1")
    List<SupermercadoProdutoViewDTO> findAllProdutos(Integer id);

    @Query("SELECT new com.bento.backend.domain.DTO.SupermercadoProdutoViewDTO(u.id.produto.id, u.id.produto.nome, u.id.produto.codigoDeBarras, " +
            "u.id.produto.categoria, u.precoProduto) " +
            "FROM SupermercadoProduto AS u WHERE u.id.supermercado.id = ?1 ORDER BY u.id.produto.nome ASC" )
    List<SupermercadoProdutoViewDTO> findAllProdutosOrderByNome(Integer id);
    
}
