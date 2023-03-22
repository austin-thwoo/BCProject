package com.laonstory.poc_be_spring.domain.examination.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDocument is a Querydsl query type for Document
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDocument extends EntityPathBase<Document> {

    private static final long serialVersionUID = -1110609640L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDocument document = new QDocument("document");

    public final StringPath docContent = createString("docContent");

    public final DateTimePath<java.time.LocalDateTime> docCreatedDate = createDateTime("docCreatedDate", java.time.LocalDateTime.class);

    public final StringPath docCreator = createString("docCreator");

    public final StringPath docDirector = createString("docDirector");

    public final StringPath docExamType = createString("docExamType");

    public final StringPath docFilePath = createString("docFilePath");

    public final NumberPath<Long> docFileSize = createNumber("docFileSize", Long.class);

    public final StringPath docStandard = createString("docStandard");

    public final EnumPath<com.laonstory.poc_be_spring.domain.examination.common.DocumentViewType> documentViewType = createEnum("documentViewType", com.laonstory.poc_be_spring.domain.examination.common.DocumentViewType.class);

    public final StringPath docUnique = createString("docUnique");

    public final QExamination examination;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDocument(String variable) {
        this(Document.class, forVariable(variable), INITS);
    }

    public QDocument(Path<? extends Document> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDocument(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDocument(PathMetadata metadata, PathInits inits) {
        this(Document.class, metadata, inits);
    }

    public QDocument(Class<? extends Document> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.examination = inits.isInitialized("examination") ? new QExamination(forProperty("examination"), inits.get("examination")) : null;
    }

}

