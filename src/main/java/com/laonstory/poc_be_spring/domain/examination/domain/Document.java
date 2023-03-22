package com.laonstory.poc_be_spring.domain.examination.domain;

import com.laonstory.poc_be_spring.domain.examination.common.DocumentViewType;
import com.laonstory.poc_be_spring.domain.examination.dto.request.DocumentCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "T_DOCUMENT")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Examination examination;
    private String docExamType; //시험분야
    private String docStandard;
    private String docContent;
    private String docDirector;
    private String docFilePath;
    private long docFileSize;
    private LocalDateTime docCreatedDate;
    private String docCreator;
    private String docUnique;

    @Enumerated(EnumType.STRING)
    private DocumentViewType documentViewType;


    public static Document place(String path, DocumentCommand documentCommand, long size) {
        return Document.builder()
                .docExamType(documentCommand.getDocExamType())
                .docStandard(documentCommand.getDocStandard())
                .docContent(documentCommand.getDocContent())
                .docDirector(documentCommand.getDocDirector())
                .docCreatedDate(LocalDateTime.now())
                .docFilePath(path)
                .docFileSize(size)
                .docCreator(documentCommand.getDocCreator())
                .docUnique(documentCommand.getDocUnique())  // 블록체인 유니크 값
                .documentViewType(DocumentViewType.BEFOREVIEW)
                .build();
    }




    public void addExamination(Examination examination) {
        this.examination = examination;
    }

    public void updateDocumentViewType(DocumentViewType documentViewType){
        this.documentViewType=documentViewType;
    }

    public void addFileSize(long size) {
        this.docFileSize= size;
    }

    public void modifyPath(String path) {
        this.docFilePath=path;
    }
}
