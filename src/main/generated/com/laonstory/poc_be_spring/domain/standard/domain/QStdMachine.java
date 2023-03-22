package com.laonstory.poc_be_spring.domain.standard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStdMachine is a Querydsl query type for StdMachine
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStdMachine extends EntityPathBase<StdMachine> {

    private static final long serialVersionUID = 1649368909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStdMachine stdMachine = new QStdMachine("stdMachine");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<StdLine, QStdLine> lines = this.<StdLine, QStdLine>createList("lines", StdLine.class, QStdLine.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final QStdCertify stdCertify;

    public QStdMachine(String variable) {
        this(StdMachine.class, forVariable(variable), INITS);
    }

    public QStdMachine(Path<? extends StdMachine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStdMachine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStdMachine(PathMetadata metadata, PathInits inits) {
        this(StdMachine.class, metadata, inits);
    }

    public QStdMachine(Class<? extends StdMachine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stdCertify = inits.isInitialized("stdCertify") ? new QStdCertify(forProperty("stdCertify"), inits.get("stdCertify")) : null;
    }

}

