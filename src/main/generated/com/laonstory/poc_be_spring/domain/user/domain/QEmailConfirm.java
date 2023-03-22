package com.laonstory.poc_be_spring.domain.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmailConfirm is a Querydsl query type for EmailConfirm
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmailConfirm extends EntityPathBase<EmailConfirm> {

    private static final long serialVersionUID = -564247045L;

    public static final QEmailConfirm emailConfirm = new QEmailConfirm("emailConfirm");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<PassCode, QPassCode> passCode = this.<PassCode, QPassCode>createList("passCode", PassCode.class, QPassCode.class, PathInits.DIRECT2);

    public QEmailConfirm(String variable) {
        super(EmailConfirm.class, forVariable(variable));
    }

    public QEmailConfirm(Path<? extends EmailConfirm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailConfirm(PathMetadata metadata) {
        super(EmailConfirm.class, metadata);
    }

}

