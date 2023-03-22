package com.laonstory.poc_be_spring.domain.examination.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DocumentUpdateCommand {

    private Long examinationId;
    private List<DocumentCommand> documentCommandList;
    private List<Long> deleteDocumentIdList;


}
