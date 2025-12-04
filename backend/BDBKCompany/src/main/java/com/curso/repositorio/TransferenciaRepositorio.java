package com.curso.repositorio;

import com.curso.domains.Transferencia;
import com.curso.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransferenciaRepositorio extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findByUsuario(Usuario usuario);

    Optional<Transferencia> findByIdAndUsuarioId(Long id, Long usuarioId);
}

