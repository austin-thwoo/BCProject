package com.laonstory.poc_be_spring.domain.examination.dto.response;

import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.examination.domain.ExaminationHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryPageResponse {
    private Long id;
    private String content;
    private String serialCode;
    private Long examinationId;
    private LocalDateTime createdDate;


    public HistoryPageResponse(ExaminationHistory examinationHistory) {
        this.id = examinationHistory.getId();
        this.content = examinationHistory.getContent();
        this.serialCode = examinationHistory.getExamination().getSerialCode();
        this.examinationId= examinationHistory.getExamination().getId();
        this.createdDate= examinationHistory.getCreatedDate();
    }

}
