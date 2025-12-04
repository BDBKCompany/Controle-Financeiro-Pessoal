package com.curso.mapper;

import com.curso.domains.ContaBancaria;
import com.curso.domains.Lancamento;
import com.curso.domains.Recebimento;
import com.curso.dto.RecebimentoInputDTO;
import com.curso.dto.RecebimentoOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecebimentoMapper {

    public Recebimento toEntity(RecebimentoInputDTO dto,
                                Lancamento lancamento,
                                ContaBancaria contaDestino) {
        if (dto == null) {
            return null;
        }

        Recebimento entity = new Recebimento();
        entity.setLancamento(lancamento);
        entity.setDataRecebimento(dto.getDataRecebimento());
        entity.setValorRecebido(dto.getValorRecebido());
        entity.setContaDestino(contaDestino);
        entity.setObservacao(dto.getObservacao());

        return entity;
    }

    public void copyToEntity(RecebimentoInputDTO dto,
                             Recebimento entity,
                             Lancamento lancamento,
                             ContaBancaria contaDestino) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setLancamento(lancamento);
        entity.setDataRecebimento(dto.getDataRecebimento());
        entity.setValorRecebido(dto.getValorRecebido());
        entity.setContaDestino(contaDestino);
        entity.setObservacao(dto.getObservacao());
    }

    public RecebimentoOutputDTO toDTO(Recebimento entity) {
        if (entity == null) {
            return null;
        }

        RecebimentoOutputDTO dto = new RecebimentoOutputDTO();
        dto.setId(entity.getId());

        if (entity.getLancamento() != null) {
            dto.setLancamentoId(entity.getLancamento().getId());
        }

        dto.setDataRecebimento(entity.getDataRecebimento());
        dto.setValorRecebido(entity.getValorRecebido());

        if (entity.getContaDestino() != null) {
            dto.setContaDestinoId(entity.getContaDestino().getId());
            dto.setContaDestinoNome(entity.getContaDestino().getApelido());
        }

        dto.setObservacao(entity.getObservacao());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        return dto;
    }

    public List<RecebimentoOutputDTO> toDTOList(List<Recebimento> entities) {
        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
