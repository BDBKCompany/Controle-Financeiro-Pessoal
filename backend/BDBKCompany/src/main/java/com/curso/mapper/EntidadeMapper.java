package com.curso.mapper;

import com.curso.dto.EntidadeInputDTO;
import com.curso.dto.EntidadeOutputDTO;
import com.curso.domains.Entidade;
import com.curso.domains.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntidadeMapper {

    public Entidade toEntity(EntidadeInputDTO dto, Usuario usuario) {
        if (dto == null) {
            return null;
        }

        Entidade entidade = new Entidade();
        entidade.setUsuario(usuario);
        entidade.setNome(dto.getNome());
        entidade.setDocumento(dto.getDocumento());
        entidade.setTipo(dto.getTipo());

        return entidade;
    }

    public void copyToEntity(EntidadeInputDTO dto, Entidade entity, Usuario usuario) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setUsuario(usuario);
        entity.setNome(dto.getNome());
        entity.setDocumento(dto.getDocumento());
        entity.setTipo(dto.getTipo());
    }

    public EntidadeOutputDTO toDTO(Entidade entity) {
        if (entity == null) {
            return null;
        }

        EntidadeOutputDTO dto = new EntidadeOutputDTO();
        dto.setId(entity.getId());

        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
            dto.setNomeUsuario(entity.getUsuario().getNome());
        }

        dto.setNome(entity.getNome());
        dto.setDocumento(entity.getDocumento());
        dto.setTipo(entity.getTipo());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        return dto;
    }

    public List<EntidadeOutputDTO> toDTOList(List<Entidade> entities) {
        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
