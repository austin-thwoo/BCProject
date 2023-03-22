package com.laonstory.poc_be_spring.domain.examination.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSample is a Querydsl query type for Sample
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QSample extends BeanPath<Sample> {

    private static final long serialVersionUID = 1077010828L;

    public static final QSample sample = new QSample("sample");

    public final NumberPath<Long> smpAmount = createNumber("smpAmount", Long.class);

    public final BooleanPath smpComeIn = createBoolean("smpComeIn");

    public final DateTimePath<java.time.LocalDateTime> smpInDate = createDateTime("smpInDate", java.time.LocalDateTime.class);

    public final StringPath smpName = createString("smpName");

    public final DateTimePath<java.time.LocalDateTime> smpOutDate = createDateTime("smpOutDate", java.time.LocalDateTime.class);

    public QSample(String variable) {
        super(Sample.class, forVariable(variable));
    }

    public QSample(Path<? extends Sample> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSample(PathMetadata metadata) {
        super(Sample.class, metadata);
    }

}

