package com.curso.mapper;

import com.curso.dto.CartaoCreditoInputDTO;
import com.curso.dto.CartaoCreditoOutputDTO;
import com.curso.domains.CartaoCredito;
import com.curso.domains.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartaoCreditoMapper {

    public CartaoCredito toEntity(CartaoCreditoInputDTO dto, Usuario usuario) {
        if (dto == null) {
            return null;
        }

        CartaoCredito cartao = new CartaoCredito();
        cartao.setUsuario(usuario);
        cartao.setBandeira(dto.getBandeira());
        cartao.setEmissor(dto.getEmissor());
        cartao.setApelido(dto.getApelido());
        cartao.setFechamentoFaturaDia(dto.getFechamentoFaturaDia());
        cartao.setVencimentoFaturaDia(dto.getVencimentoFaturaDia());
        cartao.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : Boolean.TRUE);

        return cartao;
    }

    public void copyToEntity(CartaoCreditoInputDTO dto, CartaoCredito entity, Usuario usuario) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setUsuario(usuario);
        entity.setBandeira(dto.getBandeira());
        entity.setEmissor(dto.getEmissor());
        entity.setApelido(dto.getApelido());
        entity.setFechamentoFaturaDia(dto.getFechamentoFaturaDia());
        entity.setVencimentoFaturaDia(dto.getVencimentoFaturaDia());
        entity.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : entity.getAtivo());
    }

    public CartaoCreditoOutputDTO toDTO(CartaoCredito entity) {
        if (entity == null) {
            return null;
        }

        CartaoCreditoOutputDTO dto = new CartaoCreditoOutputDTO();
        dto.setId(entity.getId());

        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
            dto.setNomeUsuario(entity.getUsuario().getNome());
        }

        dto.setBandeira(entity.getBandeira());
        dto.setEmissor(entity.getEmissor());
        dto.setApelido(entity.getApelido());
        dto.setFechamentoFaturaDia(entity.getFechamentoFaturaDia());
        dto.setVencimentoFaturaDia(entity.getVencimentoFaturaDia());
        dto.setAtivo(entity.getAtivo());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        return dto;
    }

    public List<CartaoCreditoOutputDTO> toDTOList(List<CartaoCredito> entities) {
        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}