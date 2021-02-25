package com.tomassolerlinares.Practica4.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.domain.querydsl.QAutor;
import com.tomassolerlinares.Practica4.domain.querydsl.QEditorial;
import com.tomassolerlinares.Practica4.domain.querydsl.QLibro;
import com.tomassolerlinares.Practica4.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class LibroService {
    @Autowired
    private final LibroRepository libroRepository;

    @Autowired
    private EntityManager em;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> findAllByAutor(@NonNull String name){
        QAutor qAutor = QAutor.autor;
        QLibro qLibro = QLibro.libro;

        JPAQuery<Libro> query = new JPAQuery<Libro>(em)
                .select(qLibro)
                .from(qAutor)
                .join(qAutor.libros, qLibro)
                .where(qAutor.name.contains(name));
        return query.fetch();
    }

    public List<Libro> findAllByEditorial(@NonNull String name){
        QEditorial qEditorial = QEditorial.editorial;
        QLibro qLibro = QLibro.libro;

        JPAQuery<Libro> query = new JPAQuery<Libro>(em)
                .select(qLibro)
                .from(qEditorial)
                .join(qEditorial.libros, qLibro)
                .where(qEditorial.name.contains(name));
        return query.fetch();
    }

    public List<Libro> findByEditorialAndNameLike(String editorial, String name) {

        QEditorial qEditorial = QEditorial.editorial;
        QLibro qLibro = QLibro.libro;

        JPAQuery<Libro> query = new JPAQuery<Libro>(em)
                .select(qLibro)
                .from(qEditorial.libros, qLibro)
                .join(qEditorial.libros, qLibro)
                .where(qEditorial.name.contains(editorial))
                .where(qLibro.name.contains(name));
        return query.fetch();
    }
}
