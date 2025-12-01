package com.curso.repositorio;

import com.curso.domains.CentroCusto;
import com.curso.domains.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentroCustoRepositorio extends JpaRepository<CentroCusto, Long> {

    List<CentroCusto> findByUsuario(Usuario usuario);
    Page<CentroCusto> findByUsuarioAndAtivoTrue(Usuario usuario, Pageable pageable);

    @Query("SELECT cc FROM CentroCusto cc WHERE cc.usuario.id = :usuarioId AND cc.ativo = true")
    List<CentroCusto> findAtivosByUsuarioId(@Param("usuarioId") Long usuarioId);

    Optional<CentroCusto> findByIdAndUsuarioId(Long id, Long usuarioId);

    @Query("SELECT COUNT(l) FROM Lancamento l WHERE l.centroCusto.id = :centroId")
    Long countLancamentosByCentroId(@Param("centroId") Long centroId);
}