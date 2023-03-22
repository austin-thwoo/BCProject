package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.examination.common.DocumentType;
import com.laonstory.poc_be_spring.domain.examination.common.LanguageType;
import com.laonstory.poc_be_spring.domain.examination.domain.model.Report;
import com.laonstory.poc_be_spring.domain.examination.domain.model.Sample;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SampleResponse {

    private String smpName;

    private LocalDateTime smpInDate;
    private LocalDateTime smpOutDate;
    private Long smpAmount;
    private Boolean smpComeIn;




    public SampleResponse(Sample smp){
      this.smpName=smp.getSmpName();
      this.smpInDate=smp.getSmpInDate();
      this.smpOutDate=smp.getSmpOutDate();
      this.smpAmount=smp.getSmpAmount();
      this.smpComeIn=smp.getSmpComeIn();


    }
}
