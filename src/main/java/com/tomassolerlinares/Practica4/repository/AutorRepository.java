package com.tomassolerlinares.Practica4.repository;

import com.tomassolerlinares.Practica4.domain.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutorRepository extends CrudRepository<Autor, Integer> {

    Page<Autor> findAll(Pageable pageable);
    List<Autor> findByNameLike(String name);
    Page<Autor> findByNameLike(String name, Pageable pageable);
}
