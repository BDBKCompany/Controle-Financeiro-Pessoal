package com.curso.repositorio;

import com.curso.domains.MovimentoConta;
import com.curso.domains.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentoContaRepositorio extends JpaRepository<MovimentoConta, Long> {


    List<MovimentoConta> findByConta(ContaBancaria conta);

    List<MovimentoConta> findByContaOrderByDataMovimentoDesc(ContaBancaria conta);

    List<MovimentoConta> findByReferenciaIdAndReferenciaTipo(Long referenciaId, String referenciaTipo);
}

