package com.curso.service;

import com.curso.domains.*;
import com.curso.dto.LancamentoInputDTO;
import com.curso.dto.LancamentoOutputDTO;
import com.curso.mapper.LancamentoMapper;
import com.curso.repositorio.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService {

    private final LancamentoRepositorio lancamentoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final EntidadeRepositorio entidadeRepositorio;
    private final CentroCustoRepositorio centroCustoRepositorio;
    private final ContaBancariaRepositorio contaBancariaRepositorio;
    private final CartaoCreditoRepositorio cartaoCreditoRepositorio;

    private final LancamentoMapper mapper;

    public LancamentoService(
            LancamentoRepositorio lancamentoRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            EntidadeRepositorio entidadeRepositorio,
            CentroCustoRepositorio centroCustoRepositorio,
            ContaBancariaRepositorio contaBancariaRepositorio,
            CartaoCreditoRepositorio cartaoCreditoRepositorio,
            LancamentoMapper mapper) {

        this.lancamentoRepositorio = lancamentoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.entidadeRepositorio = entidadeRepositorio;
        this.centroCustoRepositorio = centroCustoRepositorio;
        this.contaBancariaRepositorio = contaBancariaRepositorio;
        this.cartaoCreditoRepositorio = cartaoCreditoRepositorio;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<LancamentoOutputDTO> listarPorUsuario(Long usuarioId, Pageable pageable) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Page<Lancamento> page = lancamentoRepositorio.findByUsuario(usuario, pageable);

        return page.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LancamentoOutputDTO buscarPorId(Long id, Long usuarioId) {
        Lancamento lancamento = lancamentoRepositorio.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));

        return mapper.toDTO(lancamento);
    }

    @Transactional
    public LancamentoOutputDTO criar(LancamentoInputDTO dto, Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Lancamento lancamento = mapper.toEntity(dto);
        lancamento.setUsuario(usuario);

        if (dto.getEntidadeId() != null) {
            Entidade entidade = entidadeRepositorio.findById(dto.getEntidadeId())
                    .orElseThrow(() -> new RuntimeException("Entidade não encontrada"));
            lancamento.setEntidade(entidade);
        }

        if (dto.getCentroCustoId() != null) {
            CentroCusto cc = centroCustoRepositorio.findById(dto.getCentroCustoId())
                    .orElseThrow(() -> new RuntimeException("Centro de custo não encontrado"));
            lancamento.setCentroCusto(cc);
        }

        if (dto.getContaBancariaId() != null) {
            ContaBancaria conta = contaBancariaRepositorio.findById(dto.getContaBancariaId())
                    .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada"));
            lancamento.setContaBancaria(conta);
        }

        if (dto.getCartaoCreditoId() != null) {
            CartaoCredito cartao = cartaoCreditoRepositorio.findById(dto.getCartaoCreditoId())
                    .orElseThrow(() -> new RuntimeException("Cartão de crédito não encontrado"));
            lancamento.setCartaoCredito(cartao);
        }

        lancamento.validarStatusAutomatico();

        lancamento = lancamentoRepositorio.save(lancamento);

        return mapper.toDTO(lancamento);
    }

    @Transactional
    public LancamentoOutputDTO atualizar(Long id, LancamentoInputDTO dto, Long usuarioId) {
        Lancamento lancamento = lancamentoRepositorio.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));

        mapper.copyToEntity(dto, lancamento);

        if (dto.getEntidadeId() != null) {
            Entidade entidade = entidadeRepositorio.findById(dto.getEntidadeId())
                    .orElseThrow(() -> new RuntimeException("Entidade não encontrada"));
            lancamento.setEntidade(entidade);
        } else {
            lancamento.setEntidade(null);
        }

        if (dto.getCentroCustoId() != null) {
            CentroCusto cc = centroCustoRepositorio.findById(dto.getCentroCustoId())
                    .orElseThrow(() -> new RuntimeException("Centro de custo não encontrado"));
            lancamento.setCentroCusto(cc);
        } else {
            lancamento.setCentroCusto(null);
        }

        if (dto.getContaBancariaId() != null) {
            ContaBancaria conta = contaBancariaRepositorio.findById(dto.getContaBancariaId())
                    .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada"));
            lancamento.setContaBancaria(conta);
        } else {
            lancamento.setContaBancaria(null);
        }

        if (dto.getCartaoCreditoId() != null) {
            CartaoCredito cartao = cartaoCreditoRepositorio.findById(dto.getCartaoCreditoId())
                    .orElseThrow(() -> new RuntimeException("Cartão de crédito não encontrado"));
            lancamento.setCartaoCredito(cartao);
        } else {
            lancamento.setCartaoCredito(null);
        }

        lancamento.validarStatusAutomatico();

        lancamento = lancamentoRepositorio.save(lancamento);

        return mapper.toDTO(lancamento);
    }

    @Transactional
    public void excluir(Long id, Long usuarioId) {
        Lancamento lancamento = lancamentoRepositorio.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));

        Long pagamentos = lancamentoRepositorio.countPagamentos(id);
        Long recebimentos = lancamentoRepositorio.countRecebimentos(id);

        if (pagamentos > 0 || recebimentos > 0) {
            throw new RuntimeException("Não é possível excluir um lançamento que possui pagamentos ou recebimentos");
        }

        lancamentoRepositorio.delete(lancamento);
    }
}
