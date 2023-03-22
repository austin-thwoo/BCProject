package com.laonstory.poc_be_spring.domain.examination.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTest is a Querydsl query type for Test
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTest extends EntityPathBase<Test> {

    private static final long serialVersionUID = 1162373903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTest test = new QTest("test");

    public final QExamination examination;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.laonstory.poc_be_spring.domain.examination.common.TestApproveType> testApproveType = createEnum("testApproveType", com.laonstory.poc_be_spring.domain.examination.common.TestApproveType.class);

    public final StringPath testComment = createString("testComment");

    public final StringPath testDetailStandard = createString("testDetailStandard");

    public final StringPath testDirectorName = createString("testDirectorName");

    public final StringPath testGuaranteeLine = createString("testGuaranteeLine");

    public final StringPath testPartName = createString("testPartName");

    public final StringPath testProductLine = createString("testProductLine");

    public final StringPath testSubject = createString("testSubject");

    public QTest(String variable) {
        this(Test.class, forVariable(variable), INITS);
    }

    public QTest(Path<? extends Test> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTest(PathMetadata metadata, PathInits inits) {
        this(Test.class, metadata, inits);
    }

    public QTest(Class<? extends Test> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.examination = inits.isInitialized("examination") ? new QExamination(forProperty("examination"), inits.get("examination")) : null;
    }

}

