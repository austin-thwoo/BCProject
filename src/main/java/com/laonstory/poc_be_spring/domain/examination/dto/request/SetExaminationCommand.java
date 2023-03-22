package com.laonstory.poc_be_spring.domain.examination.dto.request;

import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class    SetExaminationCommand {



    private String serialCode;
    private User applicant;
    private SubCompany mftCompany;
    private SubCompany facCompany;

    public void setApplicant(User user) {
        this.applicant=user;
    }

    public void setMftCompany(SubCompany mftCompany) {
        this.mftCompany=mftCompany;
    }

    public void setFacCompany(SubCompany facCompany) {
        this.facCompany=facCompany;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode=serialCode;
    }
}
