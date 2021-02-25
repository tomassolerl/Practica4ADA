package com.tomassolerlinares.Practica4.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.domain.querydsl.QAutor;
import com.tomassolerlinares.Practica4.domain.querydsl.QLibro;
import com.tomassolerlinares.Practica4.repository.AutorRepository;
import com.tomassolerlinares.Practica4.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AutorService {
    @Autowired
    private final AutorRepository autorRepository;

    @Autowired
    private EntityManager em;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Libro> findAllByAutor(@NonNull String name) {
        QAutor qAutor = QAutor.autor;
        QLibro qLibro = QLibro.libro;

        JPAQuery<Libro> query = new JPAQuery<Libro>(em)
                .select(qLibro)
                .from(qAutor)
                .join(qAutor.libros, qLibro)
                .where(qAutor.name.contains(name));
        return query.fetch();
    }
}