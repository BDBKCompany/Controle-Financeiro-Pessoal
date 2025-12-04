package com.curso.service;

import com.curso.domains.Entidade;
import com.curso.domains.Usuario;
import com.curso.repositorio.EntidadeRepositorio;
import com.curso.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntidadeService {

    private final EntidadeRepositorio entidadeRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public EntidadeService(EntidadeRepositorio entidadeRepositorio,
                           UsuarioRepositorio usuarioRepositorio) {
        this.entidadeRepositorio = entidadeRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Entidade> listarTodas() {
        return entidadeRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public List<Entidade> listarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return entidadeRepositorio.findByUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public Entidade buscarPorId(Long id) {
        return entidadeRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com id: " + id));
    }

    @Transactional
    public Entidade criar(Entidade entidade) {
        return entidadeRepositorio.save(entidade);
    }

    @Transactional
    public Entidade atualizar(Entidade entidade) {
        return entidadeRepositorio.save(entidade);
    }

    @Transactional
    public void excluir(Long id) {
        Entidade existente = buscarPorId(id);
        entidadeRepositorio.delete(existente);
    }
}

