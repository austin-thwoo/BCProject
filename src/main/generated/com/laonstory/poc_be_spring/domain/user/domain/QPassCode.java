package com.laonstory.poc_be_spring.domain.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPassCode is a Querydsl query type for PassCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPassCode extends EntityPathBase<PassCode> {

    private static final long serialVersionUID = -2018376811L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPassCode passCode = new QPassCode("passCode");

    public final StringPath code = createString("code");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final QEmailConfirm emailConfirm;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QPassCode(String variable) {
        this(PassCode.class, forVariable(variable), INITS);
    }

    public QPassCode(Path<? extends PassCode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPassCode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPassCode(PathMetadata metadata, PathInits inits) {
        this(PassCode.class, metadata, inits);
    }

    public QPassCode(Class<? extends PassCode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emailConfirm = inits.isInitialized("emailConfirm") ? new QEmailConfirm(forProperty("emailConfirm")) : null;
    }

}

