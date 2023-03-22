package com.laonstory.poc_be_spring.domain.examination.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFactory is a Querydsl query type for Factory
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QFactory extends BeanPath<Factory> {

    private static final long serialVersionUID = 365838696L;

    public static final QFactory factory = new QFactory("factory");

    public final StringPath facManagerEmail = createString("facManagerEmail");

    public final StringPath facManagerName = createString("facManagerName");

    public final StringPath facManagerPhone = createString("facManagerPhone");

    public QFactory(String variable) {
        super(Factory.class, forVariable(variable));
    }

    public QFactory(Path<? extends Factory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFactory(PathMetadata metadata) {
        super(Factory.class, metadata);
    }

}

