package com.tomassolerlinares.Practica4.domain.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.tomassolerlinares.Practica4.domain.Editorial;
import com.tomassolerlinares.Practica4.domain.Libro;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QEditorial is a Querydsl query type for Editorial
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEditorial extends EntityPathBase<Editorial> {

    private static final long serialVersionUID = 243429330L;

    public static final QEditorial editorial = new QEditorial("editorial");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<Libro, QLibro> libros = this.<Libro, QLibro>createSet("libros", Libro.class, QLibro.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QEditorial(String variable) {
        super(Editorial.class, forVariable(variable));
    }

    public QEditorial(Path<? extends Editorial> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEditorial(PathMetadata metadata) {
        super(Editorial.class, metadata);
    }

}

