package com.curso.controller;

import com.curso.dto.CartaoCreditoInputDTO;
import com.curso.dto.CartaoCreditoOutputDTO;
import com.curso.mapper.CartaoCreditoMapper;
import com.curso.domains.CartaoCredito;
import com.curso.domains.Usuario;
import com.curso.service.CartaoCreditoService;
import com.curso.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartoes")
public class CartaoCreditoController {

    private final CartaoCreditoService cartaoCreditoService;
    private final UsuarioService usuarioService;
    private final CartaoCreditoMapper cartaoCreditoMapper;

    public CartaoCreditoController(CartaoCreditoService cartaoCreditoService,
                                   UsuarioService usuarioService,
                                   CartaoCreditoMapper cartaoCreditoMapper) {
        this.cartaoCreditoService = cartaoCreditoService;
        this.usuarioService = usuarioService;
        this.cartaoCreditoMapper = cartaoCreditoMapper;
    }

    @GetMapping
    public List<CartaoCreditoOutputDTO> listarTodos() {
        List<CartaoCredito> cartoes = cartaoCreditoService.listarTodos();
        return cartaoCreditoMapper.toDTOList(cartoes);
    }

    @GetMapping("/{id}")
    public CartaoCreditoOutputDTO buscarPorId(@PathVariable Long id) {
        CartaoCredito cartao = cartaoCreditoService.buscarPorId(id);
        return cartaoCreditoMapper.toDTO(cartao);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<CartaoCreditoOutputDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        List<CartaoCredito> cartoes = cartaoCreditoService.listarPorUsuario(usuarioId);
        return cartaoCreditoMapper.toDTOList(cartoes);
    }

    @PostMapping
    public CartaoCreditoOutputDTO criar(@RequestBody @Valid CartaoCreditoInputDTO input) {
        Usuario usuario = usuarioService.buscarPorId(input.getUsuarioId());
        CartaoCredito cartao = cartaoCreditoMapper.toEntity(input, usuario);
        CartaoCredito salvo = cartaoCreditoService.criar(cartao);
        return cartaoCreditoMapper.toDTO(salvo);
    }

    @PutMapping("/{id}")
    public CartaoCreditoOutputDTO atualizar(@PathVariable Long id,
                                            @RequestBody @Valid CartaoCreditoInputDTO input) {

        CartaoCredito existente = cartaoCreditoService.buscarPorId(id);
        Usuario usuario = usuarioService.buscarPorId(input.getUsuarioId());

        cartaoCreditoMapper.copyToEntity(input, existente, usuario);

        CartaoCredito salvo = cartaoCreditoService.atualizar(existente);
        return cartaoCreditoMapper.toDTO(salvo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        cartaoCreditoService.excluir(id);
    }
}