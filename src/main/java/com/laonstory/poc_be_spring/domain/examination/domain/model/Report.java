package com.laonstory.poc_be_spring.domain.examination.domain.model;


import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.examination.common.DocumentType;
import com.laonstory.poc_be_spring.domain.examination.common.LanguageType;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import static javax.persistence.EnumType.STRING;

@Getter
@Embeddable
public class Report {
    @Enumerated(value = STRING)
    private LanguageType rptLanguageType;

    @Enumerated(value = STRING)
    private DocumentType rptDocumentType;
    private String rptAgent;
}
