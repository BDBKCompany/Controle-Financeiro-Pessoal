package com.curso.repositorio;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " não encontrado(a) com id: " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
