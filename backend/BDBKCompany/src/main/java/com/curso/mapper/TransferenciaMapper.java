package com.curso.mapper;

import com.curso.domains.ContaBancaria;
import com.curso.domains.Transferencia;
import com.curso.domains.Usuario;
import com.curso.dto.TransferenciaInputDTO;
import com.curso.dto.TransferenciaOutputDTO;
import org.springframework.stereotype.Component;

@Component
public class TransferenciaMapper {

    // usado em: transferenciaMapper.toEntity(dto, usuario, contaOrigem, contaDestino);
    public Transferencia toEntity(TransferenciaInputDTO dto,
                                  Usuario usuario,
                                  ContaBancaria contaOrigem,
                                  ContaBancaria contaDestino) {
        if (dto == null) {
            return null;
        }

        Transferencia entity = new Transferencia();
        entity.setUsuario(usuario);
        entity.setContaOrigem(contaOrigem);
        entity.setContaDestino(contaDestino);
        entity.setData(dto.getData());
        entity.setValor(dto.getValor());
        entity.setObservacao(dto.getObservacao());

        return entity;
    }

    // usado em: transferenciaMapper.copyToEntity(dto, transferencia, usuario, contaOrigem, contaDestino);
    public void copyToEntity(TransferenciaInputDTO dto,
                             Transferencia entity,
                             Usuario usuario,
                             ContaBancaria contaOrigem,
                             ContaBancaria contaDestino) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setUsuario(usuario);
        entity.setContaOrigem(contaOrigem);
        entity.setContaDestino(contaDestino);
        entity.setData(dto.getData());
        entity.setValor(dto.getValor());
        entity.setObservacao(dto.getObservacao());
    }

    public TransferenciaOutputDTO toDTO(Transferencia entity) {
        if (entity == null) {
            return null;
        }

        TransferenciaOutputDTO dto = new TransferenciaOutputDTO();
        dto.setId(entity.getId());

        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
        }

        if (entity.getContaOrigem() != null) {
            dto.setContaOrigemId(entity.getContaOrigem().getId());
            dto.setContaOrigemNome(entity.getContaOrigem().getApelido());
        }

        if (entity.getContaDestino() != null) {
            dto.setContaDestinoId(entity.getContaDestino().getId());
            dto.setContaDestinoNome(entity.getContaDestino().getApelido());
        }

        dto.setData(entity.getData());
        dto.setValor(entity.getValor());
        dto.setObservacao(entity.getObservacao());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        return dto;
    }
}
