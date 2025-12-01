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

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CentroCustoOutputDTO> listarAtivosPorUsuario(Long usuarioId, Pageable pageable) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));
        Page<CentroCusto> centros = centroCustoRepositorio.findByUsuarioAndAtivoTrue(usuario, pageable);
        return centros.stream().map(mapper::toDTO).collect(Collectors.toList());
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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        CentroCusto centro = mapper.toEntity(dto);
        centro.setUsuario(usuario);
        if (!centro.isAtivo()) throw new RuntimeException("Centro deve ser ativo ao criar");
        centro = centroCustoRepositorio.save(centro);
        return mapper.toDTO(centro);
    }

    @Transactional
    public CentroCustoOutputDTO atualizar(Long id, CentroCustoInputDTO dto, Long usuarioId) {
        CentroCusto centro = buscarPorIdInterno(id, usuarioId);
        mapper.copyToEntity(dto, centro);
        if (!centro.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Centro não pertence ao usuário");
        }
        centro = centroCustoRepositorio.save(centro);
        return mapper.toDTO(centro);
    }

    @Transactional
    public void inativar(Long id, Long usuarioId) {
        CentroCusto centro = buscarPorIdInterno(id, usuarioId);
        if (!centro.isAtivo()) throw new RuntimeException("Centro já inativo");
        Long countVinculos = centroCustoRepositorio.countLancamentosByCentroId(id);
        if (countVinculos > 0) {
            centro.setAtivo(false);
            centroCustoRepositorio.save(centro);
        } else {
            centroCustoRepositorio.delete(centro);
        }
    }

    private CentroCusto buscarPorIdInterno(Long id, Long usuarioId) {
        return centroCustoRepositorio.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Centro não encontrado"));
    }
}