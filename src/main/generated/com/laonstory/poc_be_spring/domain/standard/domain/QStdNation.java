package com.laonstory.poc_be_spring.domain.standard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStdNation is a Querydsl query type for StdNation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStdNation extends EntityPathBase<StdNation> {

    private static final long serialVersionUID = -1441678463L;

    public static final QStdNation stdNation = new QStdNation("stdNation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<StdCertify, QStdCertify> stdCertifies = this.<StdCertify, QStdCertify>createList("stdCertifies", StdCertify.class, QStdCertify.class, PathInits.DIRECT2);

    public QStdNation(String variable) {
        super(StdNation.class, forVariable(variable));
    }

    public QStdNation(Path<? extends StdNation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStdNation(PathMetadata metadata) {
        super(StdNation.class, metadata);
    }

}

