package com.curso.repositorio;

import com.curso.domains.Lancamento;
import com.curso.domains.Recebimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecebimentoRepositorio extends JpaRepository<Recebimento, Long> {

    List<Recebimento> findByLancamento(Lancamento lancamento);
}

