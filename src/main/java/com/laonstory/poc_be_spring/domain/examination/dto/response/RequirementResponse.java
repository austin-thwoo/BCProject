package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.examination.domain.Requirement;
import com.laonstory.poc_be_spring.domain.examination.domain.model.Sample;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequirementResponse {

    private Long id;
    private String reqContent;
    private LocalDateTime reqCreatedDate;



    public RequirementResponse(Requirement requirement){
            this.id= requirement.getId();
            this.reqContent=requirement.getReqContent();
            this.reqCreatedDate=requirement.getReqCreatedDate();







    }
}
