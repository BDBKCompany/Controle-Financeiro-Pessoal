package com.curso.service;

import com.curso.domains.CentroCusto;
import com.curso.domains.Usuario;
import com.curso.dto.CentroCustoInputDTO;
import com.curso.dto.CentroCustoOutputDTO;
import com.curso.mapper.CentroCustoMapper;
import com.curso.repositorio.CentroCustoRepositorio;
import com.curso.repositorio.LancamentoRepositorio;
import com.curso.repositorio.UsuarioRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CentroCustoService {

    private final CentroCustoRepositorio centroCustoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final LancamentoRepositorio lancamentoRepositorio;
    private final CentroCustoMapper mapper;

    public CentroCustoService(CentroCustoRepositorio centroCustoRepositorio,
                              UsuarioRepositorio usuarioRepositorio,
                              LancamentoRepositorio lancamentoRepositorio,
                              CentroCustoMapper mapper) {
        this.centroCustoRepositorio = centroCustoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.lancamentoRepositorio = lancamentoRepositorio;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<CentroCustoOutputDTO> listarAtivosPorUsuario(Long usuarioId, Pageable pageable) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));
        Page<CentroCusto> page = centroCustoRepositorio.findByUsuarioAndAtivoTrue(usuario, pageable);
        return page.map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public CentroCustoOutputDTO buscarPorId(Long id, Long usuarioId) {
        CentroCusto centro = centroCustoRepositorio.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Centro de custo não encontrado para usuário: " + usuarioId));
        return mapper.toDTO(centro);
    }

    @Transactional
    public CentroCustoOutputDTO criar(CentroCustoInputDTO dto, Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));
        if (dto.getCodigo() != null && !dto.getCodigo().isBlank()) {
            Optional<CentroCusto> existente = centroCustoRepositorio.findByUsuarioAndCodigo(usuario, dto.getCodigo());
            if (existente.isPresent()) {
                throw new RuntimeException("Código já existe para este usuário");
            }
        }
        CentroCusto centro = mapper.toEntity(dto);
        centro.setUsuario(usuario);
        centro.setAtivo(true);
        centro = centroCustoRepositorio.save(centro);
        return mapper.toDTO(centro);
    }

    @Transactional
    public CentroCustoOutputDTO atualizar(Long id, CentroCustoInputDTO dto, Long usuarioId) {
        CentroCusto centro = buscarPorIdInterno(id, usuarioId);
        if (!centro.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Centro não pertence ao usuário");
        }
        if (dto.getCodigo() != null && !dto.getCodigo().isBlank()) {
            Optional<CentroCusto> existente = centroCustoRepositorio.findByUsuarioAndCodigo(centro.getUsuario(), dto.getCodigo());
            if (existente.isPresent() && !existente.get().getId().equals(id)) {
                throw new RuntimeException("Código já existe para este usuário");
            }
        }
        mapper.copyToEntity(dto, centro);
        centro = centroCustoRepositorio.save(centro);
        return mapper.toDTO(centro);
    }

    @Transactional
    public void inativar(Long id, Long usuarioId) {
        CentroCusto centro = buscarPorIdInterno(id, usuarioId);
        if (!centro.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Centro não pertence ao usuário");
        }
        if (!centro.isAtivo()) {
            throw new RuntimeException("Centro já inativo");
        }
        Long countVinculos = centroCustoRepositorio.countLancamentosByCentroId(id);
        centro.setAtivo(false);
        centroCustoRepositorio.save(centro);
    }

    private CentroCusto buscarPorIdInterno(Long id, Long usuarioId) {
        return centroCustoRepositorio.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Centro não encontrado"));
    }
}
