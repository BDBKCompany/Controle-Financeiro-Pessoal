package com.curso.service;

import com.curso.domains.ContaBancaria;
import com.curso.repositorio.ContaBancariaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaBancariaService {

    private final ContaBancariaRepositorio contaBancariaRepositorio;

    public ContaBancariaService(ContaBancariaRepositorio contaBancariaRepositorio) {
        this.contaBancariaRepositorio = contaBancariaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<ContaBancaria> listarTodas() {
        return contaBancariaRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public ContaBancaria buscarPorId(Long id) {
        return contaBancariaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<ContaBancaria> listarPorUsuario(Long usuarioId) {
        return contaBancariaRepositorio.findByUsuario_Id(usuarioId);
    }

    @Transactional
    public ContaBancaria criar(ContaBancaria conta) {
        return contaBancariaRepositorio.save(conta);
    }

    @Transactional
    public ContaBancaria atualizar(ContaBancaria conta) {
        return contaBancariaRepositorio.save(conta);
    }

    @Transactional
    public void excluir(Long id) {
        ContaBancaria existente = buscarPorId(id);
        contaBancariaRepositorio.delete(existente);
    }
}
