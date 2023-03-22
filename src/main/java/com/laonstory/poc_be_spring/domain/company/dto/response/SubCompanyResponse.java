package com.laonstory.poc_be_spring.domain.company.dto.response;



import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.models.Address;
import lombok.Getter;

@Getter
public class SubCompanyResponse {

    private final Long id;
    private final String name;
    private final String owner;
    private final String serialCode;
    private final String companyPhone;
    private final Address address;
    private final Boolean isDelete;
    private final CompanyType companyType;

    public SubCompanyResponse(SubCompany subCompany){
        this.id=subCompany.getId();
        this.name= subCompany.getName();
        this.owner = subCompany.getOwner();
        this.serialCode= subCompany.getSerialCode();
        this.companyPhone= subCompany.getCompanyPhone() != null ? subCompany.getCompanyPhone():null;
        this.address= subCompany.getAddress();
        this.isDelete= subCompany.getDeletedDate() != null;
        this.companyType=subCompany.getCompanyType();
    }
}
