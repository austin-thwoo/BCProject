package com.laonstory.poc_be_spring.domain.examination.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ExaminationDeleteCommand {

    private Long examinationId;
    private List<Long> testCommandIdList;
    private List<Long> picCommandIdList;
    private List<Long> documentCommandIdList;



}
