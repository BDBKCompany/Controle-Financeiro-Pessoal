package com.curso.mapper;

import com.curso.domains.ContaBancaria;
import com.curso.domains.Lancamento;
import com.curso.domains.Pagamento;
import com.curso.dto.PagamentoInputDTO;
import com.curso.dto.PagamentoOutputDTO;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {


    public Pagamento toEntity(PagamentoInputDTO dto,
                              Lancamento lancamento,
                              ContaBancaria contaOrigem) {

        if (dto == null) return null;

        Pagamento entity = new Pagamento();
        entity.setLancamento(lancamento);
        entity.setContaOrigem(contaOrigem);
        entity.setDataPagamento(dto.getDataPagamento());
        entity.setValorPago(dto.getValorPago());
        entity.setObservacao(dto.getObservacao());

        return entity;
    }

    public void copyToEntity(PagamentoInputDTO dto,
                             Pagamento entity,
                             Lancamento lancamento,
                             ContaBancaria contaOrigem) {
        if (dto == null || entity == null) return;

        entity.setLancamento(lancamento);
        entity.setContaOrigem(contaOrigem);
        entity.setDataPagamento(dto.getDataPagamento());
        entity.setValorPago(dto.getValorPago());
        entity.setObservacao(dto.getObservacao());
    }

    public PagamentoOutputDTO toDTO(Pagamento entity) {
        if (entity == null) return null;

        PagamentoOutputDTO dto = new PagamentoOutputDTO();
        dto.setId(entity.getId());

        if (entity.getLancamento() != null) {
            dto.setLancamentoId(entity.getLancamento().getId());
        }

        dto.setDataPagamento(entity.getDataPagamento());
        dto.setValorPago(entity.getValorPago());

        if (entity.getContaOrigem() != null) {
            dto.setContaOrigemId(entity.getContaOrigem().getId());
            dto.setContaOrigemNome(entity.getContaOrigem().getApelido());
        }

        dto.setObservacao(entity.getObservacao());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        return dto;
    }
}
