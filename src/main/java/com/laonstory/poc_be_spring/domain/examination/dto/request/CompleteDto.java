package com.laonstory.poc_be_spring.domain.examination.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CompleteDto {
    private Long documentId;
    private String path;
}
