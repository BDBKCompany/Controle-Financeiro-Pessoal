package com.curso.repositorio;

import com.curso.domains.Pagamento;
import com.curso.domains.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepositorio extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByLancamento(Lancamento lancamento);
}

