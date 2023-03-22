package com.laonstory.poc_be_spring.domain.examination.domain;

import static javax.persistence.EnumType.STRING;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationType;

import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.domain.model.*;
import com.laonstory.poc_be_spring.domain.examination.dto.request.ExaminationCommand;
import com.laonstory.poc_be_spring.domain.examination.dto.request.ExaminationModifyCommand;
import com.laonstory.poc_be_spring.domain.examination.dto.request.SetExaminationCommand;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.global.domain.BaseTimeEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "T_EXAMINATION")
public class Examination extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialCode;

    @Enumerated(value = STRING)
    private ExaminationType examinationType;

    private boolean secret;
    private String receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    private User apcManager;

    @ManyToOne(fetch = FetchType.LAZY)
    private SubCompany mftCompany;
    @Embedded
    private Manufacture manufacture;


    @ManyToOne(fetch = FetchType.LAZY)
    private SubCompany facCompany;
    @Embedded
    private Factory factory;


    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProReferencefile> proReferencefiles = new ArrayList<>();

    @Embedded
    private Product product;
    @Embedded
    private Report report;
    @Embedded
    private Sample sample;

    @Enumerated(value = STRING)
    private ExaminationStatus examinationStatus;

    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Requirement> requirements = new ArrayList<>();

    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Test> tests = new ArrayList<>();

    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Pic> pics = new ArrayList<>();

    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Document> documents = new ArrayList<>();


    // ======================================================================
    // Domain Methods
    // ======================================================================

    // 시험 신청
    public static Examination place(ExaminationCommand examinationCommand, SetExaminationCommand examinationCommand2) {

        return  Examination.builder()
                .serialCode(examinationCommand2.getSerialCode())
                .examinationType(examinationCommand.getExaminationType())
                .secret(examinationCommand.isSecret())
                .receiver(examinationCommand.getReceiver())
                .apcManager(examinationCommand2.getApplicant())
                .mftCompany(examinationCommand2.getMftCompany())
                .manufacture(examinationCommand.getManufacture())
                .facCompany(examinationCommand2.getFacCompany())
                .factory(examinationCommand.getFactory())
                .product(examinationCommand.getProduct())
                .report(examinationCommand.getReport())
                .sample(examinationCommand.getSample())
                .examinationStatus(ExaminationStatus.APPLY)
                .build();
    }


    // 시험 수정
    public void modify(//ExaminationValidator validator
                       ExaminationModifyCommand modifyCommand,
                       SetExaminationCommand setExaminationCommand) {
        this.examinationType = modifyCommand.getExaminationType();
        this.secret = modifyCommand.isSecret();
        this.receiver = modifyCommand.getReceiver();
        this.apcManager = setExaminationCommand.getApplicant();
        this.mftCompany = setExaminationCommand.getMftCompany();
        this.manufacture = modifyCommand.getManufacture();
        this.facCompany = setExaminationCommand.getFacCompany();
        this.factory = modifyCommand.getFactory();
        this.product = modifyCommand.getProduct();
        this.report = modifyCommand.getReport();
        this.sample = modifyCommand.getSample();
        this.examinationStatus = modifyCommand.getExaminationStatus();


        // 수정 가능 여부 확인
//    validator.validate(this);
    }

    // hct 측 내용 추가
    public void writeInformation(List<Test> tests, List<Pic> pics, List<Document> documents) {
        this.tests.addAll(tests);
        this.pics.addAll(pics);
        this.documents.addAll(documents);
    }


    public void addRequirementList(List<Requirement> requirementList) {
        this.requirements.addAll(requirementList);

    }

    public void addProReferenceFile(ProReferencefile proReferencefile) {
        this.proReferencefiles.add(proReferencefile);
        proReferencefile.addExamination(this);
    }

    public void addDocumentFile(Document document) {
        this.documents.add(document);
        document.addExamination(this);
    }

    public ExaminationHistory updateStatus(ExaminationStatus status) {
        this.examinationStatus = status;
        return ExaminationHistory.create(this, "update_status_" + status);
    }
}
