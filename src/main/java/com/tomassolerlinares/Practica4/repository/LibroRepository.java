package com.tomassolerlinares.Practica4.repository;

import com.tomassolerlinares.Practica4.domain.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibroRepository extends CrudRepository<Libro, Integer> {

    Page<Libro> findAll(Pageable pageable);
    List<Libro> findByNameLike(String name);
    Page<Libro> findByNameLike(String name, Pageable pageable);

    List<Libro> findByAutor(String name);
    List<Libro> findByEditorial(String name);

    List<Libro> findByEditorialAndNameLike(String editorial, String name);
}
