package com.laonstory.poc_be_spring.domain.company.dto.response;


import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.models.Address;
import lombok.Getter;

@Getter
public class CompanySubCompanyResponse {

    private final Long id;
    private final String name;
    private final String owner;
    private final String serialCode;
    private final String companyPhone;
    private final Address address;
    private final Boolean isDelete;
    private final CompanyType companyType;
    private final SimpleCompanyResponse headCompany;

    public CompanySubCompanyResponse(SubCompany suv){
        this.id=suv.getId();
        this.name= suv.getName();
        this.owner = suv.getOwner();
        this.serialCode= suv.getSerialCode();
        this.companyPhone= suv.getCompanyPhone() != null ? suv.getCompanyPhone():null;
        this.address= suv.getAddress();
        this.isDelete= suv.getDeletedDate() != null;
        this.companyType=suv.getCompanyType();
        this.headCompany=new SimpleCompanyResponse(suv.getCompany());


    }
}
