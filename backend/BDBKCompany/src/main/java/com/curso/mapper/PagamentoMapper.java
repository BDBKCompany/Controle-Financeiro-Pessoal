package com.curso.mapper;

import com.curso.domains.Pagamento;
import com.curso.dto.PagamentoInputDTO;
import com.curso.dto.PagamentoOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(PagamentoInputDTO dto) {
        if (dto == null) return null;
        Pagamento entity = new Pagamento();
        entity.setValorPago(dto.getValorPago());
        entity.setDataPagamento(dto.getDataPagamento());
        entity.setObservacao(dto.getObservacao());
        return entity;
    }

    public void copyToEntity(PagamentoInputDTO dto, Pagamento entity) {
        if (dto == null || entity == null) return;
        entity.setValorPago(dto.getValorPago());
        entity.setDataPagamento(dto.getDataPagamento());
        entity.setObservacao(dto.getObservacao());
    }

    public PagamentoOutputDTO toDTO(Pagamento entity) {
        if (entity == null) return null;
        PagamentoOutputDTO dto = new PagamentoOutputDTO();
        dto.setId(entity.getId());
        dto.setValorPago(entity.getValorPago());
        dto.setDataPagamento(entity.getDataPagamento());
        dto.setObservacao(entity.getObservacao());
        dto.setLancamentoId(entity.getLancamento().getId());
        dto.setContaOrigemId(entity.getContaOrigem().getId());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());
        return dto;
    }

    public List<PagamentoOutputDTO> toDTOList(List<Pagamento> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
