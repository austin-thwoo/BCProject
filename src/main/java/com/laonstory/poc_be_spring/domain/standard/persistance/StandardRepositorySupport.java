package com.laonstory.poc_be_spring.domain.standard.persistance;

import com.laonstory.poc_be_spring.domain.standard.domain.StdCertify;
import com.laonstory.poc_be_spring.domain.standard.domain.StdLine;
import com.laonstory.poc_be_spring.domain.standard.domain.StdMachine;
import com.laonstory.poc_be_spring.domain.standard.domain.StdNation;
import com.laonstory.poc_be_spring.domain.standard.exception.StdCertifyNotFoundException;
import com.laonstory.poc_be_spring.domain.standard.exception.StdLineNotFoundException;
import com.laonstory.poc_be_spring.domain.standard.exception.StdMachineNotFoundException;
import com.laonstory.poc_be_spring.domain.standard.exception.StdNationNotFoundException;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.laonstory.poc_be_spring.domain.standard.domain.QStdNation.stdNation;
import static com.laonstory.poc_be_spring.domain.standard.domain.QStdCertify.stdCertify;
import static com.laonstory.poc_be_spring.domain.standard.domain.QStdMachine.stdMachine;
import static com.laonstory.poc_be_spring.domain.standard.domain.QStdLine.stdLine;

@Repository
public class StandardRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public StandardRepositorySupport(JPAQueryFactory queryFactory) {
        super(StdNation.class);

        this.queryFactory = queryFactory;
    }


    public StdNation findStdNationById(Long stdNationId) {
        StdNation result = queryFactory.selectFrom(stdNation)
                .where(stdNation.id.eq(stdNationId))
                .fetchOne();
        if (result == null) {
            throw new StdNationNotFoundException(stdNationId.toString());
        }
        return result;
    }

    public StdCertify findStdCertifyById(Long stdCertifyId) {
        StdCertify result = queryFactory.selectFrom(stdCertify)
                .where(stdCertify.id.eq(stdCertifyId))
                .fetchOne();
        if (result == null) {
            throw new StdCertifyNotFoundException(stdCertifyId.toString());
        }
        return result;
    }


    public StdMachine findStdMachineById(Long stdMachineId) {
        StdMachine result = queryFactory.selectFrom(stdMachine)
                .where(stdMachine.id.eq(stdMachineId))
                .fetchOne();
        if (result == null) {
            throw new StdMachineNotFoundException(stdMachineId.toString());
        }
        return result;
    }


    public StdLine findStdLineById(Long stdLineId) {
        StdLine reuslt = queryFactory.selectFrom(stdLine)
                .where(stdLine.id.eq(stdLineId))
                .fetchOne();
        if (reuslt==null){
            throw new StdLineNotFoundException(stdLineId.toString());
        }
        return reuslt;
    }

    public List<StdNation> findNationAll(String name) {
         List<StdNation> result= queryFactory.selectFrom(stdNation)
                .where(stdNation.id.isNotNull()
                        ,stdNationName(name))
                .fetch();
         return result;

    }

    private Predicate stdNationName(String name) {
        if (name==null){
            return null;
        }
        return stdNation.name.contains(name);
    }

    public List<StdCertify> findCertifyAll(Long stdNationId, String name) {
        List<StdCertify> result=queryFactory.selectFrom(stdCertify)
                .where(stdCertify.id.isNotNull()
                        .and(stdCertifyName(name))
                        .and(stdCertify.stdNation.id.eq(stdNationId)))
                .fetch();
        return  result;

    }

    private Predicate stdCertifyName(String name) {
        if (name==null){
            return null;
        }
        return stdCertify.name.contains(name);
    }

    public List<StdMachine> findStdMachineAll(Long stdCertifyId, String name) {

        List<StdMachine> result=queryFactory.selectFrom(stdMachine)
                .where(stdMachine.id.isNotNull()
                        .and(stdMachineName(name))
                        .and(stdMachine.stdCertify.id.eq(stdCertifyId)))
                .fetch();
        return  result;

    }

    private Predicate stdMachineName(String name) {
    if (name==null){
        return null;
    }
    return stdMachine.name.contains(name);
    }


    public List<StdLine> findStdLineAll(Long stdMachineId, String name) {
       List<StdLine> result=queryFactory.selectFrom(stdLine)
                .where(stdLine.id.isNotNull()
                        .and(stdLineName(name))
                        .and(stdLine.stdMachine.id.eq(stdMachineId)))
                .fetch();
       return result;
    }

    private Predicate stdLineName(String name) {
        if (name==null){
            return null;
        }
        return stdLine.name.contains(name);
    }

    public List<StdNation> findNationAllById(List<Long> ids) {
        return queryFactory.selectFrom(stdNation)
                .where(stdNation.id.in(ids))
                .fetch();
    }

    public List<StdCertify> findCertifyAllById(List<Long> ids) {
        return queryFactory.selectFrom(stdCertify)
                .where(stdCertify.id.in(ids))
                .fetch();
    }

    public List<StdMachine> findStdMachineAllById(List<Long> ids) {
        return queryFactory.selectFrom(stdMachine)
                .where(stdMachine.id.in(ids))
                .fetch();
    }

    public List<StdLine> findStdLineAllById(List<Long> ids) {
        return queryFactory.selectFrom(stdLine)
                .where(stdLine.id.in(ids))
                .fetch();


    }
}
