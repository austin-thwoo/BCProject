package com.laonstory.poc_be_spring.domain.examination.dto.response;

import com.laonstory.poc_be_spring.domain.company.dto.response.SimpleCompanyResponse;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationType;
import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.user.dto.response.SimpleUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationResponse {
    private Long id;
    private String serialCode;
    private ExaminationType examinationType;
    private boolean secret;
    private String receiver;
    private SimpleUserResponse apcManager;
    private SimpleCompanyResponse mftCompany;
    private ManufactureResponse manufacture;
    private SimpleCompanyResponse facCompany;
    private FactoryResponse factory;
    private List<ProReferenceFileResponse> proReferencefileResponsesList;
    private ProductResponse product;
    private ReportResponse report;
    private SampleResponse sample;
    private ExaminationStatus examinationStatus;
    private List<RequirementResponse> requirementResponsesList;
    private List<TestResponse> testResponsesList;
    private List<PicResponse> picResponsesList;
    private List<DocumentResponse> documentResponseList;


    public ExaminationResponse(Examination examination) {
        this.id = examination.getId();
        this.serialCode = examination.getSerialCode();
        this.examinationType = examination.getExaminationType();
        this.secret = examination.isSecret();
        this.receiver = examination.getReceiver();
        this.apcManager = new SimpleUserResponse(examination.getApcManager());
        this.mftCompany = new SimpleCompanyResponse(examination.getMftCompany());
        this.manufacture = new ManufactureResponse(examination.getManufacture());
        this.facCompany = new SimpleCompanyResponse(examination.getFacCompany());
        this.factory = new FactoryResponse(examination.getFactory());
        this.proReferencefileResponsesList = examination.getProReferencefiles().stream().map(ProReferenceFileResponse::new).collect(Collectors.toList());
        this.product = new ProductResponse(examination.getProduct());
        this.report = new ReportResponse(examination.getReport());
        this.sample = examination.getSample() != null ? new SampleResponse(examination.getSample()) : null;
        this.examinationStatus = examination.getExaminationStatus();
        this.requirementResponsesList = examination.getRequirements().stream().map(RequirementResponse::new).collect(Collectors.toList());
        this.testResponsesList = examination.getTests().stream().map(TestResponse::new).collect(Collectors.toList());
        this.picResponsesList = examination.getPics().stream().map(PicResponse::new).collect(Collectors.toList());
        this.documentResponseList = examination.getDocuments().stream().map(DocumentResponse::new).collect(Collectors.toList());


    }

}
