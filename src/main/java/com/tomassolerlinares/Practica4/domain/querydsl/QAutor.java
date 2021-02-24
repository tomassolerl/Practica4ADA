package com.tomassolerlinares.Practica4.domain.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.tomassolerlinares.Practica4.domain.Autor;
import com.tomassolerlinares.Practica4.domain.Libro;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QAutor is a Querydsl query type for Autor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAutor extends EntityPathBase<Autor> {

    private static final long serialVersionUID = 1050649646L;

    public static final QAutor autor = new QAutor("autor");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<Libro, QLibro> libros = this.<Libro, QLibro>createSet("libros", Libro.class, QLibro.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QAutor(String variable) {
        super(Autor.class, forVariable(variable));
    }

    public QAutor(Path<? extends Autor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAutor(PathMetadata metadata) {
        super(Autor.class, metadata);
    }

}

