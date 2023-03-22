package com.laonstory.poc_be_spring.domain.examination.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DocumentCommand {


    private String docExamType; //시험분야
    private String docStandard;
    private String docContent;
    private String docDirector;

    private String docFilePath;

    private LocalDateTime docCreatedDate;
    private String docCreator;

    private String docUnique; // 블록체인 유니크 값



}
