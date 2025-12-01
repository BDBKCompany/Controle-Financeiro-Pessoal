package com.curso.mapper;

import com.curso.dto.ContaBancariaInputDTO;
import com.curso.dto.ContaBancariaOutputDTO;
import com.curso.domains.ContaBancaria;
import com.curso.domains.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContaBancariaMapper {

    public ContaBancaria toEntity(ContaBancariaInputDTO dto, Usuario usuario) {
        if (dto == null) {
            return null;
        }

        ContaBancaria conta = new ContaBancaria();
        conta.setUsuario(usuario);
        conta.setInstituicao(dto.getInstituicao());
        conta.setAgencia(dto.getAgencia());
        conta.setNumero(dto.getNumero());
        conta.setApelido(dto.getApelido());
        conta.setSaldoInicial(dto.getSaldoInicial());
        conta.setDataSaldoInicial(dto.getDataSaldoInicial());
        conta.setAtiva(dto.getAtiva() != null ? dto.getAtiva() : Boolean.TRUE);

        return conta;
    }

    public void copyToEntity(ContaBancariaInputDTO dto, ContaBancaria entity, Usuario usuario) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setUsuario(usuario);
        entity.setInstituicao(dto.getInstituicao());
        entity.setAgencia(dto.getAgencia());
        entity.setNumero(dto.getNumero());
        entity.setApelido(dto.getApelido());
        entity.setSaldoInicial(dto.getSaldoInicial());
        entity.setDataSaldoInicial(dto.getDataSaldoInicial());
        entity.setAtiva(dto.getAtiva() != null ? dto.getAtiva() : entity.getAtiva());
    }

    public ContaBancariaOutputDTO toDTO(ContaBancaria entity) {
        if (entity == null) {
            return null;
        }

        ContaBancariaOutputDTO dto = new ContaBancariaOutputDTO();
        dto.setId(entity.getId());

        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
            dto.setNomeUsuario(entity.getUsuario().getNome());
        }

        dto.setInstituicao(entity.getInstituicao());
        dto.setAgencia(entity.getAgencia());
        dto.setNumero(entity.getNumero());
        dto.setApelido(entity.getApelido());
        dto.setSaldoInicial(entity.getSaldoInicial());
        dto.setDataSaldoInicial(entity.getDataSaldoInicial());
        dto.setAtiva(entity.getAtiva());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        return dto;
    }

    public List<ContaBancariaOutputDTO> toDTOList(List<ContaBancaria> entities) {
        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
