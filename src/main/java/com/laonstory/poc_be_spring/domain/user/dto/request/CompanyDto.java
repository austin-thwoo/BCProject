package com.laonstory.poc_be_spring.domain.user.dto.request;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CompanyDto {
    private Company company;
    private List<SubCompany> companyGroups;

    public void addDto(Company company, List<SubCompany> companyGroups){
        this.company=company;
        this.companyGroups=companyGroups;
    }
}

