package com.curso.repositorio;

import com.curso.domains.CentroCusto;
import com.curso.domains.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentroCustoRepositorio extends JpaRepository<CentroCusto, Long> {

    Page<CentroCusto> findByUsuarioAndAtivoTrue(Usuario usuario, Pageable pageable);

    @Query("SELECT cc FROM CentroCusto cc WHERE cc.usuario.id = :usuarioId AND cc.ativo = true")
    Page<CentroCusto> findAtivosByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);

    Optional<CentroCusto> findByIdAndUsuarioId(Long id, Long usuarioId);

    Optional<CentroCusto> findByUsuarioAndCodigo(Usuario usuario, String codigo);

    @Query("SELECT COUNT(l) FROM Lancamento l WHERE l.centroCusto.id = :centroId")
    Long countLancamentosByCentroId(@Param("centroId") Long centroId);
}
