package com.curso.repositorio;

import com.curso.domains.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoCreditoRepositorio extends JpaRepository<CartaoCredito, Long> {

    List<CartaoCredito> findByUsuario_Id(Long usuarioId);
}

