package com.laonstory.poc_be_spring.domain.examination.persistance;

import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.domain.Document;
import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.examination.dto.response.ExaminationPageResponse;
import com.laonstory.poc_be_spring.domain.examination.dto.response.HistoryPageResponse;
import com.laonstory.poc_be_spring.domain.examination.exception.DocumentNotFoundException;
import com.laonstory.poc_be_spring.domain.examination.exception.ExaminationNotFoundException;
import com.laonstory.poc_be_spring.global.dto.DateDTO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.laonstory.poc_be_spring.domain.examination.domain.QDocument.document;
import static com.laonstory.poc_be_spring.domain.examination.domain.QExamination.examination;
import static com.laonstory.poc_be_spring.domain.examination.domain.QExaminationHistory.examinationHistory;


@Repository
public class ExaminationRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;


    public ExaminationRepositorySupport(JPAQueryFactory queryFactory) {
        super(Examination.class);
        this.queryFactory = queryFactory;
    }


    public Examination findById(Long examinationId) {
        Examination findExamination = queryFactory.selectFrom(examination)
                .where(examination.id.eq(examinationId))
                .fetchOne();
        if (findExamination == null) {
            throw new ExaminationNotFoundException(examinationId.toString());
        }
        return findExamination;
    }

    public Page<ExaminationPageResponse> findAll(Pageable pageable, String query) {
        QueryResults<ExaminationPageResponse> result = queryFactory.select(Projections.constructor(ExaminationPageResponse.class, examination))
                .from(examination)
                .orderBy(examination.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());


    }

    public Long CountByToday() {
        return queryFactory.select(examination.count().add(1).coalesce(0l)).from(examination)
                .where(examination.createdDate.after(LocalDate.now().atStartOfDay())).fetchOne();
    }


    public Page<ExaminationPageResponse> findAllByCondition(Pageable pageable, String condition, String query, UUID userId, ExaminationStatus status, DateDTO dateDTO) {
        QueryResults<ExaminationPageResponse> result = queryFactory.select(Projections.constructor(ExaminationPageResponse.class, examination))
                .from(examination)
                .where(examination.id.isNotNull()
                        .and(examination.deletedDate.isNull())
                        .and(conditionView(condition))
                        .and(statusQuery(status))
                        .and(searchQuery(query))
                        .and(dateBetween(dateDTO))
                        .and(examination.apcManager.id.eq(userId)))
                .orderBy(examination.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }


    private BooleanExpression dateBetween(DateDTO dateDTO) {

        if (dateDTO.getStartedDate() == null) {
            return null;
        }
        if (dateDTO.getEndedDate() == null) {
            return null;
        }
        return examination.createdDate.between(dateDTO.getStartedDate(), dateDTO.getEndedDate());


    }

    private BooleanExpression searchQuery(String query) {
        if (query == null) {
            return null;
        }
        return examination.product.proModelName.contains(query)
                .or(examination.apcManager.company.name.contains(query));
    }

    private BooleanExpression conditionView(String condition) {
        if (condition == null) {
            return null;
        }
        if (condition.equals("accept")) {
            return examination.examinationStatus.eq(ExaminationStatus.APPLY)
                    .or(examination.examinationStatus.eq(ExaminationStatus.APPROVE));
        }
        if (condition.equals("clientView")) {
            return examination.examinationStatus.eq(ExaminationStatus.BEFORE_REVIEW)
                    .or(examination.examinationStatus.eq(ExaminationStatus.AFTER_REVIEW))
                    .or(examination.examinationStatus.eq(ExaminationStatus.COMPLETED));
        }

        if (condition.equals("completed")) {
            return examination.examinationStatus.eq(ExaminationStatus.AllCOMPLETED);
        }
        return null;
    }

    private BooleanExpression statusQuery(ExaminationStatus status) {
        if (status == null) {
            return null;
        }

        return examination.examinationStatus.eq(status);


    }

    public PageImpl<ExaminationPageResponse> findAllByConditionManager(Pageable pageable, String condition, String query, ExaminationStatus status, DateDTO dateDTO) {
        QueryResults<ExaminationPageResponse> result = queryFactory.select(Projections.constructor(ExaminationPageResponse.class, examination))
                .from(examination)
                .where(examination.id.isNotNull()
                        .and(statusQuery(status))
                        .and(searchQuery(query))
                        .and(dateBetween(dateDTO))
                        .and(conditionViewManager(condition)))
                .orderBy(examination.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private Predicate conditionViewManager(String condition) {

        if (condition == null) {
            return null;
        }
        if (condition.equals("accept")) {
            return examination.examinationStatus.eq(ExaminationStatus.APPLY)
                    .or(examination.examinationStatus.eq(ExaminationStatus.APPROVE));
        }
        if (condition.equals("upload")) {
            return examination.examinationStatus.eq(ExaminationStatus.BEFORE_REVIEW)
                    .or(examination.examinationStatus.eq(ExaminationStatus.APPROVE))
                    .or(examination.examinationStatus.eq(ExaminationStatus.AFTER_REVIEW))
                    .or(examination.examinationStatus.eq(ExaminationStatus.COMPLETED));
        }
        if (condition.equals("report")) {
            return examination.examinationStatus.eq(ExaminationStatus.AllCOMPLETED);
        }
        return null;
    }

    public Document findDocumentById(Long documentId, Long examinationId) {
        Document findedDocument = queryFactory.selectFrom(document)
                .where(document.id.eq(documentId)
                        .and(document.examination.id.eq(examinationId)))
                .fetchOne();

        if (findedDocument == null) {
            throw new DocumentNotFoundException(documentId.toString());
        }
        return findedDocument;
    }

    public List<Document> findAllByDocumentId(List<Long> deleteDocumentIdList) {
        List<Document> documents = queryFactory.selectFrom(document)
                .where(document.id.in(deleteDocumentIdList))
                .fetch();

        if (documents == null) {
            throw new DocumentNotFoundException("IdListMiss");
        }
        return documents;
    }

    public PageImpl<HistoryPageResponse> findHistoryById(Pageable pageable, Long examinationId, DateDTO dateDTO) {
        QueryResults<HistoryPageResponse> result = queryFactory.select(Projections.constructor(HistoryPageResponse.class, examinationHistory))
                .from(examinationHistory)
                .where(examinationHistory.id.isNotNull().and(
                        idFilter(examinationId))
                        .and(historyDateBetween(dateDTO)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(examinationHistory.id.desc())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression historyDateBetween(DateDTO dateDTO) {
        if (dateDTO.getStartedDate() == null) {
            return null;
        }
        if (dateDTO.getEndedDate() == null) {
            return null;
        }
        return examinationHistory.createdDate.between(dateDTO.getStartedDate(), dateDTO.getEndedDate());
    }

    private BooleanExpression idFilter(Long examinationId) {
        if (examinationId == null) {
            return null;
        }
        return examinationHistory.examination.id.eq(examinationId);
    }


}
