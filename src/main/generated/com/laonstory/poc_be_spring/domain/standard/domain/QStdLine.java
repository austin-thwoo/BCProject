package com.laonstory.poc_be_spring.domain.standard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStdLine is a Querydsl query type for StdLine
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStdLine extends EntityPathBase<StdLine> {

    private static final long serialVersionUID = -1199316306L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStdLine stdLine = new QStdLine("stdLine");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QStdMachine stdMachine;

    public QStdLine(String variable) {
        this(StdLine.class, forVariable(variable), INITS);
    }

    public QStdLine(Path<? extends StdLine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStdLine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStdLine(PathMetadata metadata, PathInits inits) {
        this(StdLine.class, metadata, inits);
    }

    public QStdLine(Class<? extends StdLine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stdMachine = inits.isInitialized("stdMachine") ? new QStdMachine(forProperty("stdMachine"), inits.get("stdMachine")) : null;
    }

}

