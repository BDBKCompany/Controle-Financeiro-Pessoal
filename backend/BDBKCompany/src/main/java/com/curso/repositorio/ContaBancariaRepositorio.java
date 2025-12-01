package com.curso.repositorio;

import com.curso.domains.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaBancariaRepositorio extends JpaRepository<ContaBancaria, Long> {

    List<ContaBancaria> findByUsuario_Id(Long usuarioId);
}
