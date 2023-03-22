package com.laonstory.poc_be_spring.domain.standard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStdCertify is a Querydsl query type for StdCertify
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStdCertify extends EntityPathBase<StdCertify> {

    private static final long serialVersionUID = 1492993374L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStdCertify stdCertify = new QStdCertify("stdCertify");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<StdMachine, QStdMachine> machines = this.<StdMachine, QStdMachine>createList("machines", StdMachine.class, QStdMachine.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final QStdNation stdNation;

    public QStdCertify(String variable) {
        this(StdCertify.class, forVariable(variable), INITS);
    }

    public QStdCertify(Path<? extends StdCertify> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStdCertify(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStdCertify(PathMetadata metadata, PathInits inits) {
        this(StdCertify.class, metadata, inits);
    }

    public QStdCertify(Class<? extends StdCertify> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stdNation = inits.isInitialized("stdNation") ? new QStdNation(forProperty("stdNation")) : null;
    }

}

