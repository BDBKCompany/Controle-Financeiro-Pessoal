package com.curso.repositorio;

import com.curso.domains.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepositorio extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByLancamentoId(Long lancamentoId);
}
