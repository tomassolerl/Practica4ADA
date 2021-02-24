package com.tomassolerlinares.Practica4.service;

import com.tomassolerlinares.Practica4.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LibroService {
    @Autowired
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
}
