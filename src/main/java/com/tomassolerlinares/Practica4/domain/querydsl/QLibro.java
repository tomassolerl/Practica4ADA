package com.tomassolerlinares.Practica4.domain.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.tomassolerlinares.Practica4.domain.Libro;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QLibro is a Querydsl query type for Libro
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLibro extends EntityPathBase<Libro> {

    private static final long serialVersionUID = 1060433677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLibro libro = new QLibro("libro");

    protected QAutor autor;

    public final StringPath description = createString("description");

    protected QEditorial editorial;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QLibro(String variable) {
        this(Libro.class, forVariable(variable), INITS);
    }

    public QLibro(Path<? extends Libro> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLibro(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLibro(PathMetadata metadata, PathInits inits) {
        this(Libro.class, metadata, inits);
    }

    public QLibro(Class<? extends Libro> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.autor = inits.isInitialized("autor") ? new QAutor(forProperty("autor")) : null;
        this.editorial = inits.isInitialized("editorial") ? new QEditorial(forProperty("editorial")) : null;
    }

    public QAutor autor() {
        if (autor == null) {
            autor = new QAutor(forProperty("autor"));
        }
        return autor;
    }

    public QEditorial editorial() {
        if (editorial == null) {
            editorial = new QEditorial(forProperty("editorial"));
        }
        return editorial;
    }

}

