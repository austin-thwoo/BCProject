package com.laonstory.poc_be_spring.global.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLogHistory is a Querydsl query type for LogHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogHistory extends EntityPathBase<LogHistory> {

    private static final long serialVersionUID = -474219703L;

    public static final QLogHistory logHistory = new QLogHistory("logHistory");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedDate = _super.deletedDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ipAddress = createString("ipAddress");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath url = createString("url");

    public QLogHistory(String variable) {
        super(LogHistory.class, forVariable(variable));
    }

    public QLogHistory(Path<? extends LogHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogHistory(PathMetadata metadata) {
        super(LogHistory.class, metadata);
    }

}

