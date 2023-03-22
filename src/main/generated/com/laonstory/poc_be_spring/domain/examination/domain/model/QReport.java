package com.laonstory.poc_be_spring.domain.examination.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReport is a Querydsl query type for Report
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QReport extends BeanPath<Report> {

    private static final long serialVersionUID = 1052164374L;

    public static final QReport report = new QReport("report");

    public final StringPath rptAgent = createString("rptAgent");

    public final EnumPath<com.laonstory.poc_be_spring.domain.examination.common.DocumentType> rptDocumentType = createEnum("rptDocumentType", com.laonstory.poc_be_spring.domain.examination.common.DocumentType.class);

    public final EnumPath<com.laonstory.poc_be_spring.domain.examination.common.LanguageType> rptLanguageType = createEnum("rptLanguageType", com.laonstory.poc_be_spring.domain.examination.common.LanguageType.class);

    public QReport(String variable) {
        super(Report.class, forVariable(variable));
    }

    public QReport(Path<? extends Report> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReport(PathMetadata metadata) {
        super(Report.class, metadata);
    }

}

