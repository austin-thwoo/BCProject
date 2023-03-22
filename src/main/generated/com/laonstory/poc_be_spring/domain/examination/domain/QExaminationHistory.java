package com.laonstory.poc_be_spring.domain.examination.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExaminationHistory is a Querydsl query type for ExaminationHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExaminationHistory extends EntityPathBase<ExaminationHistory> {

    private static final long serialVersionUID = -1030451424L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExaminationHistory examinationHistory = new QExaminationHistory("examinationHistory");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final QExamination examination;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QExaminationHistory(String variable) {
        this(ExaminationHistory.class, forVariable(variable), INITS);
    }

    public QExaminationHistory(Path<? extends ExaminationHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExaminationHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExaminationHistory(PathMetadata metadata, PathInits inits) {
        this(ExaminationHistory.class, metadata, inits);
    }

    public QExaminationHistory(Class<? extends ExaminationHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.examination = inits.isInitialized("examination") ? new QExamination(forProperty("examination"), inits.get("examination")) : null;
    }

}

