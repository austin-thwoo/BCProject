package com.laonstory.poc_be_spring.domain.examination.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QManufacture is a Querydsl query type for Manufacture
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QManufacture extends BeanPath<Manufacture> {

    private static final long serialVersionUID = -143120865L;

    public static final QManufacture manufacture = new QManufacture("manufacture");

    public final StringPath mftManagerEmail = createString("mftManagerEmail");

    public final StringPath mftManagerName = createString("mftManagerName");

    public final StringPath mftManagerPhone = createString("mftManagerPhone");

    public QManufacture(String variable) {
        super(Manufacture.class, forVariable(variable));
    }

    public QManufacture(Path<? extends Manufacture> path) {
        super(path.getType(), path.getMetadata());
    }

    public QManufacture(PathMetadata metadata) {
        super(Manufacture.class, metadata);
    }

}

