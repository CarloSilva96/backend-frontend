package com.bento.backend.repository;

import com.bento.backend.domain.DTO.SupermercadoDTO;
import com.bento.backend.domain.Supermercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupermercadoRepository extends JpaRepository<Supermercado, Integer> {
    @Query("SELECT u FROM Supermercado u ORDER BY u.nome ASC")
    List<SupermercadoDTO> findAllSupermercadosPorNome();

    @Query("SELECT u FROM Supermercado u")
    List<SupermercadoDTO> findAllSupermercadosS();
}
