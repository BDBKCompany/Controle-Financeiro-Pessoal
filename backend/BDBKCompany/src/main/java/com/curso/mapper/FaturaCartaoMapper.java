package com.curso.mapper;

import com.curso.dto.FaturaCartaoInputDTO;
import com.curso.dto.FaturaCartaoOutputDTO;
import com.curso.domains.CartaoCredito;
import com.curso.domains.FaturaCartao;
import com.curso.Enum.StatusFatura;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FaturaCartaoMapper {

    public FaturaCartao toEntity(FaturaCartaoInputDTO dto, CartaoCredito cartao) {
        if (dto == null) return null;
        FaturaCartao f = new FaturaCartao();
        f.setCartaoCredito(cartao);
        f.setPeriodoInicio(dto.getPeriodoInicio());
        f.setPeriodoFim(dto.getPeriodoFim());
        f.setValorTotal(dto.getValorTotal());
        if (dto.getStatusId() != null) {
            f.setStatus(StatusFatura.toEnum(dto.getStatusId()));
        } else {
            f.setStatus(StatusFatura.ABERTA);
        }
        f.setDataFechamento(dto.getDataFechamento());
        f.setDataVencimento(dto.getDataVencimento());
        f.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : Boolean.TRUE);
        return f;
    }

    public void copyToEntity(FaturaCartaoInputDTO dto, FaturaCartao entity, CartaoCredito cartao) {
        if (dto == null || entity == null) return;
        entity.setCartaoCredito(cartao);
        entity.setPeriodoInicio(dto.getPeriodoInicio());
        entity.setPeriodoFim(dto.getPeriodoFim());
        entity.setValorTotal(dto.getValorTotal());
        if (dto.getStatusId() != null) {
            entity.setStatus(StatusFatura.toEnum(dto.getStatusId()));
        }
        entity.setDataFechamento(dto.getDataFechamento());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : entity.getAtivo());
    }

    public FaturaCartaoOutputDTO toDTO(FaturaCartao entity) {
        if (entity == null) return null;
        FaturaCartaoOutputDTO dto = new FaturaCartaoOutputDTO();
        dto.setId(entity.getId());
        if (entity.getCartaoCredito() != null) {
            dto.setCartaoCreditoId(entity.getCartaoCredito().getId());
            dto.setCartaoApelido(entity.getCartaoCredito().getApelido());
        }
        dto.setPeriodoInicio(entity.getPeriodoInicio());
        dto.setPeriodoFim(entity.getPeriodoFim());
        dto.setValorTotal(entity.getValorTotal());
        if (entity.getStatus() != null) {
            dto.setStatusId(entity.getStatus().getId());
            dto.setStatusDescricao(entity.getStatus().getDescricao());
        }
        dto.setDataFechamento(entity.getDataFechamento());
        dto.setDataVencimento(entity.getDataVencimento());
        dto.setAtivo(entity.getAtivo());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());
        return dto;
    }

    public List<FaturaCartaoOutputDTO> toDTOList(List<FaturaCartao> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
