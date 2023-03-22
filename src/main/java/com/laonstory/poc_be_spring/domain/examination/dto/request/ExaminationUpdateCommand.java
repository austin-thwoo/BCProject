package com.laonstory.poc_be_spring.domain.examination.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ExaminationUpdateCommand {

    private Long examinationId;
    private List<TestCommand> testCommandList;
    private List<PicCommand> picCommandList;
    private List<DocumentCommand> documentCommandList;
    private List<Long> deleteTestIdList;
    private List<Long> deletePicIdList;
    private List<Long> deleteDocumnetIdList;



}
