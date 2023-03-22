package com.laonstory.poc_be_spring.domain.examination.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRequirement is a Querydsl query type for Requirement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRequirement extends EntityPathBase<Requirement> {

    private static final long serialVersionUID = 2113068838L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRequirement requirement = new QRequirement("requirement");

    public final QExamination examination;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath reqContent = createString("reqContent");

    public final DateTimePath<java.time.LocalDateTime> reqCreatedDate = createDateTime("reqCreatedDate", java.time.LocalDateTime.class);

    public QRequirement(String variable) {
        this(Requirement.class, forVariable(variable), INITS);
    }

    public QRequirement(Path<? extends Requirement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRequirement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRequirement(PathMetadata metadata, PathInits inits) {
        this(Requirement.class, metadata, inits);
    }

    public QRequirement(Class<? extends Requirement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.examination = inits.isInitialized("examination") ? new QExamination(forProperty("examination"), inits.get("examination")) : null;
    }

}

