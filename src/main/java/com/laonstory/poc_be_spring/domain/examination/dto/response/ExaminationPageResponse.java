package com.laonstory.poc_be_spring.domain.examination.dto.response;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.domain.common.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationPageResponse {
    private Long id;
    private String serialCode;
    private String proStandard;
    private String picManagerName;
    private Long applicantCompanyId;
    private String applicantCompanyName;
    private String proModelName;
    private LocalDateTime examinationCreatedDate;
    private LocalDateTime proExpectedDate;
    private String receiver;
    private ExaminationStatus examinationStatus;
    private LocalDateTime deleteDate;



    public ExaminationPageResponse(Examination examination) {
        this.id = examination.getId();
        this.serialCode = examination.getSerialCode();
        this.proStandard=examination.getProduct()==null? null: examination.getProduct().getProStandard();
        this.picManagerName=examination.getPics().isEmpty()? null:examination.getPics().get(0).getPicManagerName();
        this.applicantCompanyId=examination.getApcManager().getCompany().getId();
        this.applicantCompanyName=examination.getApcManager().getCompany().getName();
        this.proModelName=examination.getProduct().getProModelName();
        this.examinationCreatedDate=examination.getCreatedDate();
        this.proExpectedDate=examination.getProduct().getProExpectedDate();
        this.receiver=examination.getReceiver();
        this.examinationStatus=examination.getExaminationStatus();
        this.deleteDate= examination.getDeletedDate() ==null? null: examination.getDeletedDate();
    }

}
