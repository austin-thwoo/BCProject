package com.laonstory.poc_be_spring.domain.company.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubCompany is a Querydsl query type for SubCompany
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubCompany extends EntityPathBase<SubCompany> {

    private static final long serialVersionUID = -1371735962L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubCompany subCompany = new QSubCompany("subCompany");

    public final com.laonstory.poc_be_spring.global.domain.QBaseTimeEntity _super = new com.laonstory.poc_be_spring.global.domain.QBaseTimeEntity(this);

    public final com.laonstory.poc_be_spring.domain.models.QAddress address;

    public final QCompany company;

    public final StringPath companyPhone = createString("companyPhone");

    public final EnumPath<com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType> companyType = createEnum("companyType", com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedDate = _super.deletedDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath owner = createString("owner");

    public final StringPath serialCode = createString("serialCode");

    public QSubCompany(String variable) {
        this(SubCompany.class, forVariable(variable), INITS);
    }

    public QSubCompany(Path<? extends SubCompany> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubCompany(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubCompany(PathMetadata metadata, PathInits inits) {
        this(SubCompany.class, metadata, inits);
    }

    public QSubCompany(Class<? extends SubCompany> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.laonstory.poc_be_spring.domain.models.QAddress(forProperty("address")) : null;
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company"), inits.get("company")) : null;
    }

}

