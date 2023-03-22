package com.laonstory.poc_be_spring.domain.company.dto.response;


import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.models.Address;
import lombok.Getter;

@Getter
public class CompanyResponse {

    private final Long id;
    private final String name;
    private final String owner;
    private final String serialCode;
    private final String companyPhone;
    private final Address address;
    private final Boolean isDelete;

    public CompanyResponse(Company company){
        this.id=company.getId();
        this.name= company.getName();
        this.owner = company.getOwner();
        this.serialCode= company.getSerialCode();
        this.companyPhone= company.getCompanyPhone() != null ? company.getCompanyPhone():null;
        this.address= company.getAddress();
        this.isDelete= company.getDeletedDate() != null;
    }
}
