package com.curso.mapper;

import com.curso.domains.ContaBancaria;
import com.curso.domains.MovimentoConta;
import com.curso.dto.MovimentoContaInputDTO; // se você ainda não tiver, pode remover toEntity/copy
import com.curso.dto.MovimentoContaOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovimentoContaMapper {

    public MovimentoConta toEntity(MovimentoContaInputDTO dto, ContaBancaria conta) {
        if (dto == null) {
            return null;
        }

        MovimentoConta entity = new MovimentoConta();
        entity.setConta(conta);
        entity.setDataMovimento(dto.getDataMovimento());
        entity.setTipo(MovimentoConta.TipoTransacao.valueOf(dto.getTipo()));
        entity.setValor(dto.getValor());
        entity.setHistorico(dto.getHistorico());
        entity.setReferenciaId(dto.getReferenciaId());
        entity.setReferenciaTipo(dto.getReferenciaTipo());

        return entity;
    }

    public void copyToEntity(MovimentoContaInputDTO dto, MovimentoConta entity, ContaBancaria conta) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setConta(conta);
        entity.setDataMovimento(dto.getDataMovimento());
        entity.setTipo(MovimentoConta.TipoTransacao.valueOf(dto.getTipo()));
        entity.setValor(dto.getValor());
        entity.setHistorico(dto.getHistorico());
        entity.setReferenciaId(dto.getReferenciaId());
        entity.setReferenciaTipo(dto.getReferenciaTipo());
    }

    public MovimentoContaOutputDTO toDTO(MovimentoConta entity) {
        if (entity == null) {
            return null;
        }

        MovimentoContaOutputDTO dto = new MovimentoContaOutputDTO();
        dto.setId(entity.getId());

        if (entity.getConta() != null) {
            dto.setContaId(entity.getConta().getId());
            dto.setContaNome(entity.getConta().getApelido());
        }

        dto.setDataMovimento(entity.getDataMovimento());
        dto.setTipo(entity.getTipo() != null ? entity.getTipo().name() : null);
        dto.setValor(entity.getValor());
        dto.setHistorico(entity.getHistorico());
        dto.setReferenciaId(entity.getReferenciaId());
        dto.setReferenciaTipo(entity.getReferenciaTipo());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        return dto;
    }

    public List<MovimentoContaOutputDTO> toDTOList(List<MovimentoConta> entities) {
        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
