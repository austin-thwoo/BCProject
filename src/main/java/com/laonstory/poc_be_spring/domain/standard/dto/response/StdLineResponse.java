package com.laonstory.poc_be_spring.domain.standard.dto.response;

import com.laonstory.poc_be_spring.domain.standard.domain.StdLine;
import com.laonstory.poc_be_spring.domain.standard.domain.StdNation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StdLineResponse {
    private Long stdLineId;
    private String stdLineName;


    public StdLineResponse(StdLine stdLine){
        this.stdLineId=stdLine.getId();
        this.stdLineName=stdLine.getName();
    }


}
