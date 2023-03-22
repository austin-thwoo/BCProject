package com.laonstory.poc_be_spring.domain.company.persistance;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanyResponse;
import com.laonstory.poc_be_spring.domain.company.exception.CompanyNotFoundException;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.laonstory.poc_be_spring.domain.company.domain.QCompany.company;
import static com.laonstory.poc_be_spring.domain.user.domain.QUser.user;
@Repository
public class CompanyRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public CompanyRepositorySupport(JPAQueryFactory queryFactory) {
        super(Company.class);
        this.queryFactory = queryFactory;
    }

    public Page<CompanyResponse>  findAll(Pageable pageable, String query) {

        QueryResults<CompanyResponse> result =queryFactory.select(Projections.constructor(CompanyResponse.class,company)).from(company)
                .where(companyQuery(query))
                .orderBy(company.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }


    private BooleanExpression companyQuery(String query) {
        if(query==null){
            return null;
        }
        return company.serialCode.eq(query);
    }

    public Company findById(Long companyId) {
        Company oneCompany=queryFactory.selectFrom(company)
                .where(company.id.eq(companyId))
                .fetchOne();


        if (oneCompany==null){
            throw new CompanyNotFoundException(companyId.toString());
        }
        return oneCompany;
    }

    public List<Company> findAllById(List<Long> companyIds) {
        return queryFactory.selectFrom(company)
                .where(company.id.in(companyIds))
                .fetch();
    }


//    public void findNews(User principal) {
//
//        queryFactory.select();
//
//    }



}
