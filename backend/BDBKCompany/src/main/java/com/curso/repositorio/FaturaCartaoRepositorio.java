package com.curso.repositorio;

import com.curso.domains.FaturaCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaturaCartaoRepositorio extends JpaRepository<FaturaCartao, Long> {

    List<FaturaCartao> findByCartaoCredito_Id(Long cartaoId);

    List<FaturaCartao> findByCartaoCredito_Usuario_Id(Long usuarioId);
}
