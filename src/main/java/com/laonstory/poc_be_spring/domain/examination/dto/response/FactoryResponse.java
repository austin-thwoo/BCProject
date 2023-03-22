package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.examination.domain.model.Factory;
import com.laonstory.poc_be_spring.domain.examination.domain.model.Manufacture;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FactoryResponse {


    private String facManagerName;
    private String facManagerEmail;
    private String facManagerPhone;



    public FactoryResponse(Factory factory){
        this.facManagerName=factory.getFacManagerName() !=null?factory.getFacManagerName():null;
        this.facManagerEmail=factory.getFacManagerEmail()!=null?factory.getFacManagerEmail():null;
        this.facManagerPhone=factory.getFacManagerPhone()!=null?factory.getFacManagerPhone():null;



    }
}
