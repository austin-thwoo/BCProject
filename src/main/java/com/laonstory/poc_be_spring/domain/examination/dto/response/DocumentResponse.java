package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.examination.common.DocumentViewType;
import com.laonstory.poc_be_spring.domain.examination.domain.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Slf4j
public class DocumentResponse {

    private Long id;
    private String docExamType; //시험분야
    private String docStandard;
    private String docContent;
    private String docDirector;
    private String docFilePath;
    private LocalDateTime docCreatedDate;
    private String docCreator;
    private DocumentViewType documentViewType;
    private long docFileSize;
    private String docUnique;


    public DocumentResponse(Document document){

    this.id=document.getId();
    this.docExamType=document.getDocExamType();
    this.docStandard=document.getDocStandard();
    this.docContent=document.getDocContent();
    this.docDirector=document.getDocDirector();
    this.docFilePath=document.getDocFilePath();
    this.docCreatedDate=document.getDocCreatedDate();
    this.docCreator=document.getDocCreator();
    this.documentViewType=document.getDocumentViewType();
    this.docFileSize=document.getDocFileSize();
    this.docUnique=document.getDocUnique();
    }
}
