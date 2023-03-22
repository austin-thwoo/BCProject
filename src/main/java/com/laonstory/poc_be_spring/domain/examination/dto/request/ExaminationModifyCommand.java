package com.laonstory.poc_be_spring.domain.examination.dto.request;

import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationType;
import com.laonstory.poc_be_spring.domain.examination.domain.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ExaminationModifyCommand {

    private Long examinationId;


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

    private ExaminationStatus examinationStatus;

    private List<RequirementCommand> requirements;
    private List<Long> deleteProReferenceIdList;
    private List<Long> deleteRequirementeList;

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
