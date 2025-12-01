package com.curso.mapper;

import com.curso.domains.CentroCusto;
import com.curso.domains.Usuario;
import com.curso.dto.CentroCustoInputDTO;
import com.curso.dto.CentroCustoOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CentroCustoMapper {

    public CentroCusto toEntity(CentroCustoInputDTO dto) {
        if (dto == null) return null;
        CentroCusto entity = new CentroCusto();
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
        entity.setAtivo(true);
        return entity;
    }

    public void copyToEntity(CentroCustoInputDTO dto, CentroCusto entity) {
        if (dto == null || entity == null) return;
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
    }

    public CentroCustoOutputDTO toDTO(CentroCusto entity) {
        if (entity == null) return null;
        CentroCustoOutputDTO dto = new CentroCustoOutputDTO();
        dto.setId(entity.getId());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        dto.setAtivo(entity.isAtivo());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());
        return dto;
    }

    public List<CentroCustoOutputDTO> toDTOList(List<CentroCusto> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}