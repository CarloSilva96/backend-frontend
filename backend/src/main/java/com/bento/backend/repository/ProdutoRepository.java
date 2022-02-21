package com.bento.backend.repository;

import com.bento.backend.domain.DTO.ProdutoDTO;
import com.bento.backend.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Query("SELECT u FROM Produto u ORDER BY u.nome ASC")
    List<ProdutoDTO> findAllProdutosPorNome();

    @Query("SELECT u FROM Produto u")
    List<ProdutoDTO> findAllProdutosP();
}
