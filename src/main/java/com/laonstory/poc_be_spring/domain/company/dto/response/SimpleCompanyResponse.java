package com.laonstory.poc_be_spring.domain.company.dto.response;


import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.models.Address;
import lombok.Getter;

@Getter
public class SimpleCompanyResponse {

    private final Long id;
    private final String name;


    public SimpleCompanyResponse(Company company){
        this.id=company.getId();
        this.name= company.getName();

    }
    public SimpleCompanyResponse(SubCompany company){
        this.id=company.getId();
        this.name= company.getName();

    }
}
