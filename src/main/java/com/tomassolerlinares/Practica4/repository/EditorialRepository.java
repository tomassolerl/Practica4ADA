package com.tomassolerlinares.Practica4.repository;

import com.tomassolerlinares.Practica4.domain.Editorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EditorialRepository extends CrudRepository<Editorial, Integer> {

    Page<Editorial> findAll(Pageable pageable);
    List<Editorial> findByNameLike(String name);
    Page<Editorial> findByNameLike(String name, Pageable pageable);
}
