package com.laonstory.poc_be_spring.domain.examination.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPic is a Querydsl query type for Pic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPic extends EntityPathBase<Pic> {

    private static final long serialVersionUID = 453134189L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPic pic = new QPic("pic");

    public final QExamination examination;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath picComment = createString("picComment");

    public final StringPath picDetailStandard = createString("picDetailStandard");

    public final DateTimePath<java.time.LocalDateTime> picManageDate = createDateTime("picManageDate", java.time.LocalDateTime.class);

    public final StringPath picManagerName = createString("picManagerName");

    public final EnumPath<com.laonstory.poc_be_spring.domain.examination.common.PicMangeType> picManageType = createEnum("picManageType", com.laonstory.poc_be_spring.domain.examination.common.PicMangeType.class);

    public QPic(String variable) {
        this(Pic.class, forVariable(variable), INITS);
    }

    public QPic(Path<? extends Pic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPic(PathMetadata metadata, PathInits inits) {
        this(Pic.class, metadata, inits);
    }

    public QPic(Class<? extends Pic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.examination = inits.isInitialized("examination") ? new QExamination(forProperty("examination"), inits.get("examination")) : null;
    }

}

