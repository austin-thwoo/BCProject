package com.laonstory.poc_be_spring.domain.company.persistance;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.company.dto.request.SubCompanyCommand;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanySubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.exception.AlreadySubCompanyException;
import com.laonstory.poc_be_spring.domain.company.exception.CompanyNotFoundException;
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

import java.util.List;

import static com.laonstory.poc_be_spring.domain.company.domain.QSubCompany.subCompany;

@Repository
public class SubCompanyRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public SubCompanyRepositorySupport(JPAQueryFactory queryFactory) {
        super(Company.class);
        this.queryFactory = queryFactory;
    }


    public SubCompany findById(Long subCompanyId) {
        SubCompany sub = queryFactory.selectFrom(subCompany)
                .where(subCompany.id.eq(subCompanyId)).fetchOne();
        if (sub == null) {
            throw new CompanyNotFoundException(subCompanyId.toString());
        }
        return sub;
    }

    public List<SubCompany> findAllBySubCompanyId(List<Long> subCompanyId) {
        return queryFactory.selectFrom(subCompany)
                .where(subCompany.id.in(subCompanyId))
                .fetch();
    }

    public SubCompany findSubCompanyByCompanyId(Long subCompanyId) {
        SubCompany foundSubCompany = queryFactory.selectFrom(subCompany)
                .where(subCompany.id.eq(subCompanyId))
                .fetchOne();
        if (foundSubCompany == null) {
            throw new CompanyNotFoundException(subCompanyId.toString());
        }
        return foundSubCompany;
    }


    public Page<CompanySubCompanyResponse> findAllPageBySubCompanyId(Pageable pageable, String query, Long companyId, CompanyType companyType){
        QueryResults<CompanySubCompanyResponse> result = queryFactory
                .select(Projections.constructor(CompanySubCompanyResponse.class, subCompany)).from(subCompany)
                .where(subCompany.id.isNotNull()
                        .and(companyQuery(companyId))
                        .and(searchQuery(query))
                        .and(companyTypeQuery(companyType)))
                .orderBy(subCompany.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());


    }

    private BooleanExpression companyTypeQuery(CompanyType companyType) {
        if (companyType==null){
            return null;
        }
        return subCompany.companyType.eq(companyType);
    }

    private BooleanExpression companyQuery(Long companyId) {
        if (companyId==null){
            return null;
        }
            return subCompany.company.id.eq(companyId);
    }

    private BooleanExpression searchQuery(String query) {
        if (query == null) {
            return null;
        }
        return subCompany.serialCode.eq(query);

    }


    public void duplicationCheck(SubCompanyCommand subCompanyCommand, Long companyId) {
        SubCompany foundSubCompany=queryFactory.selectFrom(subCompany)
                .where(subCompany.id.isNotNull()
                        .and(subCompany.serialCode.eq(subCompanyCommand.getSerialCode())
                                .and(subCompany.name.eq(subCompanyCommand.getName())
                                        .and(subCompany.companyType.eq(subCompanyCommand.getCompanyType())))))
                .fetchOne();
        if (foundSubCompany!=null){
            throw new AlreadySubCompanyException("해당하는 subCompany Id : "+foundSubCompany.getId().toString());
        }
    }


}
