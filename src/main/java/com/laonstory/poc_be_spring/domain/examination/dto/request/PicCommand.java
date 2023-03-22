package com.laonstory.poc_be_spring.domain.examination.dto.request;

import com.laonstory.poc_be_spring.domain.examination.common.PicMangeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PicCommand {


    private String picDetailStandard;
    private String picManagerName;
    private PicMangeType picManageType;
    private LocalDateTime picManageDate;
    private String picComment;





}
