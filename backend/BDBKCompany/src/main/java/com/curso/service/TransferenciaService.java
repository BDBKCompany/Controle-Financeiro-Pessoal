package com.curso.service;

import com.curso.domains.ContaBancaria;
import com.curso.domains.MovimentoConta;
import com.curso.domains.Transferencia;
import com.curso.domains.Usuario;
import com.curso.dto.TransferenciaInputDTO;
import com.curso.dto.TransferenciaOutputDTO;
import com.curso.mapper.TransferenciaMapper;
import com.curso.repositorio.ContaBancariaRepositorio;
import com.curso.repositorio.MovimentoContaRepositorio;
import com.curso.repositorio.TransferenciaRepositorio;
import com.curso.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepositorio transferenciaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ContaBancariaRepositorio contaBancariaRepositorio;
    private final MovimentoContaRepositorio movimentoContaRepositorio;
    private final TransferenciaMapper transferenciaMapper;

    public TransferenciaService(TransferenciaRepositorio transferenciaRepositorio,
                                UsuarioRepositorio usuarioRepositorio,
                                ContaBancariaRepositorio contaBancariaRepositorio,
                                MovimentoContaRepositorio movimentoContaRepositorio,
                                TransferenciaMapper transferenciaMapper) {
        this.transferenciaRepositorio = transferenciaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.contaBancariaRepositorio = contaBancariaRepositorio;
        this.movimentoContaRepositorio = movimentoContaRepositorio;
        this.transferenciaMapper = transferenciaMapper;
    }

    @Transactional(readOnly = true)
    public List<TransferenciaOutputDTO> listarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Transferencia> transferencias = transferenciaRepositorio.findByUsuario(usuario);

        return transferencias.stream()
                .map(transferenciaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public TransferenciaOutputDTO buscarPorId(Long id, Long usuarioId) {
        Transferencia transferencia = transferenciaRepositorio.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Transferência não encontrada"));

        return transferenciaMapper.toDTO(transferencia);
    }

    @Transactional
    public TransferenciaOutputDTO criar(TransferenciaInputDTO dto, Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ContaBancaria contaOrigem = contaBancariaRepositorio.findById(dto.getContaOrigemId())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));

        ContaBancaria contaDestino = contaBancariaRepositorio.findById(dto.getContaDestinoId())
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        Transferencia transferencia = transferenciaMapper.toEntity(dto, usuario, contaOrigem, contaDestino);
        transferencia = transferenciaRepositorio.save(transferencia);

        // Movimento de DÉBITO na conta de origem
        MovimentoConta movDebito = new MovimentoConta();
        movDebito.setConta(contaOrigem);
        movDebito.setDataMovimento(dto.getData());
        movDebito.setTipo(MovimentoConta.TipoTransacao.DEBITO);
        movDebito.setValor(dto.getValor());
        movDebito.setHistorico("Transferência para conta " + contaDestino.getApelido());
        movDebito.setReferenciaId(transferencia.getId());
        movDebito.setReferenciaTipo("TRANSFERENCIA");
        movimentoContaRepositorio.save(movDebito);

        MovimentoConta movCredito = new MovimentoConta();
        movCredito.setConta(contaDestino);
        movCredito.setDataMovimento(dto.getData());
        movCredito.setTipo(MovimentoConta.TipoTransacao.CREDITO);
        movCredito.setValor(dto.getValor());
        movCredito.setHistorico("Transferência de conta " + contaOrigem.getApelido());
        movCredito.setReferenciaId(transferencia.getId());
        movCredito.setReferenciaTipo("TRANSFERENCIA");
        movimentoContaRepositorio.save(movCredito);

        return transferenciaMapper.toDTO(transferencia);
    }

    @Transactional
    public TransferenciaOutputDTO atualizar(Long id, TransferenciaInputDTO dto, Long usuarioId) {
        Transferencia transferencia = transferenciaRepositorio.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Transferência não encontrada"));

        Usuario usuario = transferencia.getUsuario();

        ContaBancaria contaOrigem = contaBancariaRepositorio.findById(dto.getContaOrigemId())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));

        ContaBancaria contaDestino = contaBancariaRepositorio.findById(dto.getContaDestinoId())
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        transferenciaMapper.copyToEntity(dto, transferencia, usuario, contaOrigem, contaDestino);
        transferencia = transferenciaRepositorio.save(transferencia);

        return transferenciaMapper.toDTO(transferencia);
    }

    @Transactional
    public void excluir(Long id, Long usuarioId) {
        Transferencia transferencia = transferenciaRepositorio.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Transferência não encontrada"));

        transferenciaRepositorio.delete(transferencia);
    }
}

