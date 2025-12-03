package com.curso.repositorio;

import com.curso.domains.Lancamento;
import com.curso.domains.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LancamentoRepositorio extends JpaRepository<Lancamento, Long> {

    Page<Lancamento> findByUsuario(Usuario usuario, Pageable pageable);

    Optional<Lancamento> findByIdAndUsuarioId(Long id, Long usuarioId);

    @Query("SELECT l FROM Lancamento l WHERE l.usuario.id = :usuarioId AND l.status = :status")
    List<Lancamento> findByStatus(
            @Param("usuarioId") Long usuarioId,
            @Param("status") Integer status);

    @Query("SELECT l FROM Lancamento l WHERE l.usuario.id = :usuarioId AND l.dataVencimento = :data")
    List<Lancamento> findByDataVencimento(
            @Param("usuarioId") Long usuarioId,
            @Param("data") LocalDate data);

    @Query("SELECT l FROM Lancamento l WHERE l.usuario.id = :usuarioId AND l.dataVencimento BETWEEN :inicio AND :fim")
    List<Lancamento> findByPeriodo(
            @Param("usuarioId") Long usuarioId,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

    @Query("SELECT COUNT(p) FROM Pagamento p WHERE p.lancamento.id = :lancamentoId")
    Long countPagamentos(@Param("lancamentoId") Long lancamentoId);

    @Query("SELECT COUNT(r) FROM Recebimento r WHERE r.lancamento.id = :lancamentoId")
    Long countRecebimentos(@Param("lancamentoId") Long lancamentoId);

    @Query("""
        SELECT l FROM Lancamento l
        WHERE l.usuario.id = :usuarioId
          AND (:status IS NULL OR l.status = :status)
          AND (:tipo IS NULL OR l.tipo = :tipo)
          AND (:descricao IS NULL OR LOWER(l.descricao) LIKE LOWER(CONCAT('%', :descricao, '%')))
          AND (:dataIni IS NULL OR l.dataVencimento >= :dataIni)
          AND (:dataFim IS NULL OR l.dataVencimento <= :dataFim)
    """)
    Page<Lancamento> filtrar(
            @Param("usuarioId") Long usuarioId,
            @Param("status") Integer status,
            @Param("tipo") Integer tipo,
            @Param("descricao") String descricao,
            @Param("dataIni") LocalDate dataIni,
            @Param("dataFim") LocalDate dataFim,
            Pageable pageable);
}
