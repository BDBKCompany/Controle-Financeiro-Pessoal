package com.curso.mapper;

import com.curso.domains.Lancamento;
import com.curso.dto.LancamentoInputDTO;
import com.curso.dto.LancamentoOutputDTO;
import org.springframework.stereotype.Component;

@Component
public class LancamentoMapper {

    public Lancamento toEntity(LancamentoInputDTO dto) {
        if (dto == null) {
            return null;
        }

        Lancamento entity = new Lancamento();
        entity.setTipo(dto.getTipo());
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setDataCompetencia(dto.getDataCompetencia());

        return entity;
    }

    public void copyToEntity(LancamentoInputDTO dto, Lancamento entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setTipo(dto.getTipo());
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setDataCompetencia(dto.getDataCompetencia());
    }

    public LancamentoOutputDTO toDTO(Lancamento entity) {
        if (entity == null) {
            return null;
        }

        LancamentoOutputDTO dto = new LancamentoOutputDTO();

        dto.setId(entity.getId());

        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
        }

        dto.setTipo(entity.getTipo());
        dto.setDescricao(entity.getDescricao());
        dto.setValor(entity.getValor());
        dto.setValorBaixado(entity.getValorBaixado());
        dto.setStatus(entity.getStatus());

        dto.setDataVencimento(entity.getDataVencimento());
        dto.setDataCompetencia(entity.getDataCompetencia());

        if (entity.getEntidade() != null) {
            dto.setEntidadeId(entity.getEntidade().getId());
            dto.setEntidadeNome(entity.getEntidade().getNome());
        }

        if (entity.getCentroCusto() != null) {
            dto.setCentroCustoId(entity.getCentroCusto().getId());
            dto.setCentroCustoNome(entity.getCentroCusto().getNome());
        }

        if (entity.getContaBancaria() != null) {
            dto.setContaBancariaId(entity.getContaBancaria().getId());
            dto.setContaBancariaNome(entity.getContaBancaria().getApelido());
        }

        if (entity.getCartaoCredito() != null) {
            dto.setCartaoCreditoId(entity.getCartaoCredito().getId());
            dto.setCartaoCreditoNome(entity.getCartaoCredito().getApelido());
        }

        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        return dto;
    }
}
