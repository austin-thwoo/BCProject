package com.laonstory.poc_be_spring.domain.standard.dto.response;

import com.laonstory.poc_be_spring.domain.standard.domain.StdCertify;
import com.laonstory.poc_be_spring.domain.standard.domain.StdNation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StdCertifyResponse {
    private Long stdCertifyId;
    private String stdCertifyName;


    public StdCertifyResponse(StdCertify stdCertify) {
        this.stdCertifyId = stdCertify.getId();
        this.stdCertifyName = stdCertify.getName();


    }
}
