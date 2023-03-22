package com.laonstory.poc_be_spring.domain.user.persistance;

import com.laonstory.poc_be_spring.domain.user.domain.EmailConfirm;
import com.laonstory.poc_be_spring.domain.user.domain.PassCode;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.laonstory.poc_be_spring.domain.user.domain.QPassCode.passCode;


@Repository
public class EmailConfirmRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public EmailConfirmRepositorySupport(JPAQueryFactory queryFactory) {
        super(EmailConfirm.class);
        this.queryFactory = queryFactory;
    }


    public PassCode findCodeByEmailLast(String userEmail) {
        return queryFactory.selectFrom(passCode)
                .where(passCode.emailConfirm.email.eq(userEmail))
                //TODO : 패스코드 테이블 한번 날리고 오더바이 지ㄷ워
                .orderBy(passCode.createDate.desc())
                .fetchFirst();
    }
    public List<PassCode> findCodeAllByEmail(String userEmail) {
        return queryFactory.selectFrom(passCode)
                .where(passCode.emailConfirm.email.eq(userEmail))
                .fetch();
    }

    public List<PassCode> findAllByEmailConfirm(String userEmail) {
        return queryFactory.selectFrom(passCode)
                .where(passCode.emailConfirm.email.eq(userEmail)).fetch();
    }
}
