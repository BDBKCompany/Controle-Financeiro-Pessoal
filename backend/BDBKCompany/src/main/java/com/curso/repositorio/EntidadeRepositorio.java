package com.curso.repositorio;

import com.curso.domains.Entidade;
import com.curso.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntidadeRepositorio extends JpaRepository<Entidade, Long> {

    List<Entidade> findByUsuario(Usuario usuario);
}
