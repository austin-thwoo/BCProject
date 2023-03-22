package com.laonstory.poc_be_spring.domain.examination.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QProduct extends BeanPath<Product> {

    private static final long serialVersionUID = 1148247373L;

    public static final QProduct product = new QProduct("product");

    public final BooleanPath proAgent = createBoolean("proAgent");

    public final StringPath proChildDiffer = createString("proChildDiffer");

    public final StringPath proChildName = createString("proChildName");

    public final DateTimePath<java.time.LocalDateTime> proCompletedDate = createDateTime("proCompletedDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> proExpectedDate = createDateTime("proExpectedDate", java.time.LocalDateTime.class);

    public final StringPath proModelName = createString("proModelName");

    public final StringPath proModelType = createString("proModelType");

    public final StringPath proName = createString("proName");

    public final BooleanPath proNew = createBoolean("proNew");

    public final StringPath proSellNation = createString("proSellNation");

    public final StringPath proStandard = createString("proStandard");

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

