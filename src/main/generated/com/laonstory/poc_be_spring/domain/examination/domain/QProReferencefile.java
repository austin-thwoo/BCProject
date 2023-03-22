package com.laonstory.poc_be_spring.domain.examination.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProReferencefile is a Querydsl query type for ProReferencefile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProReferencefile extends EntityPathBase<ProReferencefile> {

    private static final long serialVersionUID = 1422387351L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProReferencefile proReferencefile = new QProReferencefile("proReferencefile");

    public final QExamination examination;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath originalName = createString("originalName");

    public final StringPath path = createString("path");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public QProReferencefile(String variable) {
        this(ProReferencefile.class, forVariable(variable), INITS);
    }

    public QProReferencefile(Path<? extends ProReferencefile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProReferencefile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProReferencefile(PathMetadata metadata, PathInits inits) {
        this(ProReferencefile.class, metadata, inits);
    }

    public QProReferencefile(Class<? extends ProReferencefile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.examination = inits.isInitialized("examination") ? new QExamination(forProperty("examination"), inits.get("examination")) : null;
    }

}

