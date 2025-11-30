package com.curso.mapper;

import com.curso.dto.UsuarioInputDTO;
import com.curso.dto.UsuarioOutputDTO;
import com.curso.domains.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioInputDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }

    public void copyToEntity(UsuarioInputDTO dto, Usuario entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
    }

    public UsuarioOutputDTO toDTO(Usuario entity) {
        if (entity == null) {
            return null;
        }

        UsuarioOutputDTO dto = new UsuarioOutputDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());
        return dto;
    }

    public List<UsuarioOutputDTO> toDTOList(List<Usuario> entities) {
        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
