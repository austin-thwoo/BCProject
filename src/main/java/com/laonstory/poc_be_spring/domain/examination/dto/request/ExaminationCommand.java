package com.laonstory.poc_be_spring.domain.examination.dto.request;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationType;
import com.laonstory.poc_be_spring.domain.examination.domain.Document;
import com.laonstory.poc_be_spring.domain.examination.domain.Pic;
import com.laonstory.poc_be_spring.domain.examination.domain.Requirement;
import com.laonstory.poc_be_spring.domain.examination.domain.Test;
import com.laonstory.poc_be_spring.domain.examination.domain.model.*;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ExaminationCommand {




    private ExaminationType examinationType;
    private boolean secret;
    private String receiver;

    private UUID applicantId;

    private Long mftCompanyId;

    private Manufacture manufacture;


    private Long facCompanyId;
    private Factory factory;

    private Product product;

    private Report report;

    private Sample sample;

    private List<RequirementCommand> requirements;


//    private List<Test> tests;
//
//    private List<Pic> pics;
//
//    private List<Document> documents;

//    public void setApplicant(User user) {
//        this.applicant=user;
//    }
//
//    public void setMftCompany(Company mftCompany) {
//        this.mftCompany=mftCompany;
//    }
//
//    public void setFacCompany(Company facCompany) {
//        this.facCompany=facCompany;
//    }
//
//    public void setSerialCode(String serialCode) {
//        this.serialCode=serialCode;
//    }
}
