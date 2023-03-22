package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.examination.domain.model.Manufacture;
import com.laonstory.poc_be_spring.domain.examination.domain.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ManufactureResponse {


    private String mftManagerName;
    private String mftManagerEmail;
    private String mftManagerPhone;





    public ManufactureResponse(Manufacture manufacture){
        this.mftManagerName=manufacture.getMftManagerName()!= null? manufacture.getMftManagerName():null;
        this.mftManagerEmail=manufacture.getMftManagerEmail()!= null? manufacture.getMftManagerEmail():null;
        this.mftManagerPhone=manufacture.getMftManagerPhone()!= null? manufacture.getMftManagerPhone():null;

    }
}
