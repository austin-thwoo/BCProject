package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.examination.common.DocumentType;
import com.laonstory.poc_be_spring.domain.examination.common.LanguageType;
import com.laonstory.poc_be_spring.domain.examination.domain.model.Product;
import com.laonstory.poc_be_spring.domain.examination.domain.model.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportResponse {

    private LanguageType rptLanguageType;
    private DocumentType rptDocumentType;
    private String rptAgent;


    public ReportResponse(Report rpt) {
        this.rptLanguageType = rpt.getRptLanguageType();
        this.rptDocumentType = rpt.getRptDocumentType();
        this.rptAgent = rpt.getRptAgent() != null ? rpt.getRptAgent() : null;

    }
}
