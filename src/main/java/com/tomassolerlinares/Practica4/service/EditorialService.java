package com.tomassolerlinares.Practica4.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.domain.querydsl.QEditorial;
import com.tomassolerlinares.Practica4.domain.querydsl.QLibro;
import com.tomassolerlinares.Practica4.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class EditorialService {
    @Autowired
    private final EditorialRepository editorialRepository;

    @Autowired
    private EntityManager em;

    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
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
}