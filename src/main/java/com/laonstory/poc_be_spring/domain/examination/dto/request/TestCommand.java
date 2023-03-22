package com.laonstory.poc_be_spring.domain.examination.dto.request;

import com.laonstory.poc_be_spring.domain.examination.common.TestApproveType;
import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
public class TestCommand {


    private String testProductLine;
    private String testGuaranteeLine;

    private String testSubject;
    private String testPartName;
    private String testDetailStandard;
    private String testDirectorName;
    private String testComment;

    private TestApproveType testApproveType;






}
