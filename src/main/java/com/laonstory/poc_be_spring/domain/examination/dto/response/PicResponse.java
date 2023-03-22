package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.examination.common.PicMangeType;
import com.laonstory.poc_be_spring.domain.examination.common.TestApproveType;
import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.examination.domain.Pic;
import com.laonstory.poc_be_spring.domain.examination.domain.Test;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PicResponse {

    private Long id;
    private String picDetailStandard;
    private String picManagerName;
    private PicMangeType picManageType;
    private LocalDateTime picManageDate;
    private String picComment;

    public PicResponse(Pic pic){
    this.id=pic.getId();
    this.picDetailStandard=pic.getPicDetailStandard();
    this.picManagerName=pic.getPicManagerName();
    this.picManageType=pic.getPicManageType();
    this.picManageDate=pic.getPicManageDate();
    this.picComment=pic.getPicComment();


            






    }
}
