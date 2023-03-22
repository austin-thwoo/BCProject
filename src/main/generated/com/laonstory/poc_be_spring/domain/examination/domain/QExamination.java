package com.laonstory.poc_be_spring.domain.examination.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExamination is a Querydsl query type for Examination
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExamination extends EntityPathBase<Examination> {

    private static final long serialVersionUID = -2056235308L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExamination examination = new QExamination("examination");

    public final com.laonstory.poc_be_spring.global.domain.QBaseTimeEntity _super = new com.laonstory.poc_be_spring.global.domain.QBaseTimeEntity(this);

    public final com.laonstory.poc_be_spring.domain.user.domain.QUser apcManager;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedDate = _super.deletedDate;

    public final ListPath<Document, QDocument> documents = this.<Document, QDocument>createList("documents", Document.class, QDocument.class, PathInits.DIRECT2);

    public final EnumPath<com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus> examinationStatus = createEnum("examinationStatus", com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus.class);

    public final EnumPath<com.laonstory.poc_be_spring.domain.examination.common.ExaminationType> examinationType = createEnum("examinationType", com.laonstory.poc_be_spring.domain.examination.common.ExaminationType.class);

    public final com.laonstory.poc_be_spring.domain.company.domain.QSubCompany facCompany;

    public final com.laonstory.poc_be_spring.domain.examination.domain.model.QFactory factory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.laonstory.poc_be_spring.domain.examination.domain.model.QManufacture manufacture;

    public final com.laonstory.poc_be_spring.domain.company.domain.QSubCompany mftCompany;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final ListPath<Pic, QPic> pics = this.<Pic, QPic>createList("pics", Pic.class, QPic.class, PathInits.DIRECT2);

    public final com.laonstory.poc_be_spring.domain.examination.domain.model.QProduct product;

    public final ListPath<ProReferencefile, QProReferencefile> proReferencefiles = this.<ProReferencefile, QProReferencefile>createList("proReferencefiles", ProReferencefile.class, QProReferencefile.class, PathInits.DIRECT2);

    public final StringPath receiver = createString("receiver");

    public final com.laonstory.poc_be_spring.domain.examination.domain.model.QReport report;

    public final ListPath<Requirement, QRequirement> requirements = this.<Requirement, QRequirement>createList("requirements", Requirement.class, QRequirement.class, PathInits.DIRECT2);

    public final com.laonstory.poc_be_spring.domain.examination.domain.model.QSample sample;

    public final BooleanPath secret = createBoolean("secret");

    public final StringPath serialCode = createString("serialCode");

    public final ListPath<Test, QTest> tests = this.<Test, QTest>createList("tests", Test.class, QTest.class, PathInits.DIRECT2);

    public QExamination(String variable) {
        this(Examination.class, forVariable(variable), INITS);
    }

    public QExamination(Path<? extends Examination> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExamination(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExamination(PathMetadata metadata, PathInits inits) {
        this(Examination.class, metadata, inits);
    }

    public QExamination(Class<? extends Examination> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.apcManager = inits.isInitialized("apcManager") ? new com.laonstory.poc_be_spring.domain.user.domain.QUser(forProperty("apcManager"), inits.get("apcManager")) : null;
        this.facCompany = inits.isInitialized("facCompany") ? new com.laonstory.poc_be_spring.domain.company.domain.QSubCompany(forProperty("facCompany"), inits.get("facCompany")) : null;
        this.factory = inits.isInitialized("factory") ? new com.laonstory.poc_be_spring.domain.examination.domain.model.QFactory(forProperty("factory")) : null;
        this.manufacture = inits.isInitialized("manufacture") ? new com.laonstory.poc_be_spring.domain.examination.domain.model.QManufacture(forProperty("manufacture")) : null;
        this.mftCompany = inits.isInitialized("mftCompany") ? new com.laonstory.poc_be_spring.domain.company.domain.QSubCompany(forProperty("mftCompany"), inits.get("mftCompany")) : null;
        this.product = inits.isInitialized("product") ? new com.laonstory.poc_be_spring.domain.examination.domain.model.QProduct(forProperty("product")) : null;
        this.report = inits.isInitialized("report") ? new com.laonstory.poc_be_spring.domain.examination.domain.model.QReport(forProperty("report")) : null;
        this.sample = inits.isInitialized("sample") ? new com.laonstory.poc_be_spring.domain.examination.domain.model.QSample(forProperty("sample")) : null;
    }

}

