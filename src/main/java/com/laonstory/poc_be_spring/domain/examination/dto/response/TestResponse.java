package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.examination.common.TestApproveType;
import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.examination.domain.Requirement;
import com.laonstory.poc_be_spring.domain.examination.domain.Test;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TestResponse {
    private Long id;
    private String testProductLine;
    private String testGuaranteeLine;
    private String testSubject;
    private String testPartName;
    private String testDetailStandard;
    private String testDirectorName;
    private String testComment;

    private TestApproveType testApproveType;

    public TestResponse(Test test){
    this.id= test.getId();
    this.testProductLine=test.getTestProductLine();
    this.testGuaranteeLine=test.getTestGuaranteeLine();
    this.testSubject=test.getTestSubject();
    this.testPartName=test.getTestPartName();
    this.testDetailStandard=test.getTestDetailStandard();
    this.testDirectorName=test.getTestDirectorName();
    this.testComment=test.getTestComment();
    this.testApproveType= test.getTestApproveType();


            






    }
}
