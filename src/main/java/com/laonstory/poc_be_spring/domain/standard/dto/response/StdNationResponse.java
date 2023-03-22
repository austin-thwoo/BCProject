package com.laonstory.poc_be_spring.domain.standard.dto.response;

import com.laonstory.poc_be_spring.domain.standard.domain.StdNation;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.domain.common.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StdNationResponse {
    private Long stdNationId;
    private String stdNationName;


    public StdNationResponse(StdNation stdNation) {
        this.stdNationId = stdNation.getId();
        this.stdNationName = stdNation.getName();

    }


}
