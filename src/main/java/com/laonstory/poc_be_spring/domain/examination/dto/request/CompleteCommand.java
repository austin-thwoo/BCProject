package com.laonstory.poc_be_spring.domain.examination.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CompleteCommand {
    private Long examinationId;
    private List<CompleteDto> completeDtoList;
}
