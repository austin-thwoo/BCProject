package com.laonstory.poc_be_spring.domain.examination.application;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.company.persistance.CompanyRepositorySupport;
import com.laonstory.poc_be_spring.domain.company.persistance.SubCompanyJpaRepository;
import com.laonstory.poc_be_spring.domain.company.persistance.SubCompanyRepositorySupport;
import com.laonstory.poc_be_spring.domain.examination.common.DocumentViewType;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.domain.*;
import com.laonstory.poc_be_spring.domain.examination.dto.request.*;
import com.laonstory.poc_be_spring.domain.examination.exception.*;
import com.laonstory.poc_be_spring.domain.examination.persistance.*;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.exception.UserMisMatchException;
import com.laonstory.poc_be_spring.domain.user.persistance.UserRepositorySupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.laonstory.poc_be_spring.global.utils.ResourceUtil.deleteFile;
import static com.laonstory.poc_be_spring.global.utils.ResourceUtil.saveFileList;

@Transactional
@Service
@Slf4j
public class ExaminationCommandService {

    private final RequirementJpaRepository requirementJpaRepository;
    private final ExaminationJpaRepository examinationJpaRepository;
    private final ExaminationRepositorySupport examinationRepositorySupport;
    private final ExaminationHistoryJpaRepository examinationHistoryJpaRepository;
    private final CompanyRepositorySupport companyRepositorySupport;
    private final UserRepositorySupport userRepositorySupport;
    private final TestJpaRepository testJpaRepository;
    private final PicJpaRepository picJpaRepository;
    private final DocumentJpaRepository documentJpaRepository;
    private final ProReferenceFileJpaRepository proReferenceFileJpaRepository;
    private final SubCompanyRepositorySupport subCompanyRepositorySupport;
    private final SubCompanyJpaRepository subCompanyJpaRepository;

    public ExaminationCommandService(RequirementJpaRepository requirementJpaRepository, ExaminationJpaRepository examinationJpaRepository,
                                     ExaminationRepositorySupport examinationRepositorySupport,
                                     ExaminationHistoryJpaRepository examinationHistoryJpaRepository, CompanyRepositorySupport companyRepositorySupport,
                                     UserRepositorySupport userRepositorySupport, TestJpaRepository testJpaRepository, PicJpaRepository picJpaRepository,
                                     DocumentJpaRepository documentJpaRepository, ProReferenceFileJpaRepository proReferenceFileJpaRepository, SubCompanyRepositorySupport subCompanyRepositorySupport, SubCompanyJpaRepository subCompanyJpaRepository) {
        this.requirementJpaRepository = requirementJpaRepository;
        this.examinationJpaRepository = examinationJpaRepository;
        this.examinationRepositorySupport = examinationRepositorySupport;
        this.examinationHistoryJpaRepository = examinationHistoryJpaRepository;
        this.companyRepositorySupport = companyRepositorySupport;
        this.userRepositorySupport = userRepositorySupport;
        this.testJpaRepository = testJpaRepository;
        this.picJpaRepository = picJpaRepository;
        this.documentJpaRepository = documentJpaRepository;
        this.proReferenceFileJpaRepository = proReferenceFileJpaRepository;
        this.subCompanyRepositorySupport = subCompanyRepositorySupport;
        this.subCompanyJpaRepository = subCompanyJpaRepository;
    }


    public boolean save(User principal, ExaminationCommand examinationCommand, List<MultipartFile> files) {


        didCheck(principal);
        SetExaminationCommand setExaminationCommand = new SetExaminationCommand();

        User applicant = getUser(examinationCommand.getApplicantId());
//        Company facCompany = getCompany(examinationCommand.getFacCompanyId());
        SubCompany facCompany = getSubCompany(examinationCommand.getFacCompanyId());
        //        Company mftCompany = getCompany(examinationCommand.getMftCompanyId());
        SubCompany mftCompany = getSubCompany(examinationCommand.getMftCompanyId());

        setExaminationCommand.setApplicant(applicant);
        setExaminationCommand.setMftCompany(mftCompany);
        setExaminationCommand.setFacCompany(facCompany);
        setExaminationCommand.setSerialCode(createSerialCode());


        Examination examination = Examination.place(examinationCommand, setExaminationCommand);


        if (examinationCommand.getRequirements() != null) {
            List<Requirement> requirementList = new ArrayList<>();
            for (RequirementCommand requirement : examinationCommand.getRequirements()) {
                requirementList.add(Requirement.create(requirement.getReqContent(), examination));
            }
            examination.addRequirementList(requirementList);
            requirementJpaRepository.saveAll(requirementList);
        }

        historySaver(ExaminationHistory.create(examination, "update_status_APPLY"));
        examinationJpaRepository.save(examination);
        createProReferenceFiles(files, examination);//파일 있으면
        return true;
    }

    private SubCompany getSubCompany(Long subCompanyId) {
        return subCompanyRepositorySupport.findById(subCompanyId);
    }

    private void didCheck(User principal) {
        String DID = getUser(principal.getId()).getDidAddress();
        if (DID == null || DID.isBlank()) {
            throw new DIDNotFoundException(principal.getId().toString());
        }
    }

    private void createProReferenceFiles(List<MultipartFile> files, Examination examination) {
        if (files != null) {
            List<String> pathList = saveFileList(files, examination.getId(), "proReference");
            for (int i = 0; i < pathList.size(); i++) {
                examination.addProReferenceFile(ProReferencefile.
                        create(pathList.get(i),
                                files.get(i).getOriginalFilename(),
                                files.get(i).getSize()));
            }
        }
        if (files == null) {

        }
    }


    public String createSerialCode() {
        String date = LocalDateTime.now().toString();

        String year = date.substring(2, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        String todayCount = examinationRepositorySupport.CountByToday().toString();
        log.info("date'{}'", date);

        return "HCT-RF-" + year + month + day + todayCount;
    }


    private Company getCompany(Long mftCompanyId) {
        return companyRepositorySupport.findById(mftCompanyId);
    }

    private User getUser(UUID applicantId) {
        return userRepositorySupport.findById(applicantId);
    }

    public boolean addExamination(User principal, ExaminationUpdateCommand updateCommand, List<MultipartFile> files) {

        Examination examination = getExaminationById(updateCommand.getExaminationId());

        List<Test> tests = testPlace(updateCommand.getTestCommandList(), examination, updateCommand.getDeleteTestIdList());
        List<Pic> pics = picPlace(updateCommand.getPicCommandList(), examination, updateCommand.getDeletePicIdList());
        List<Document> documents = documentPlace(files, updateCommand.getDocumentCommandList(), updateCommand.getDeleteDocumnetIdList(), examination);

        saveMethod(tests, pics, documents);
        examination.writeInformation(tests, pics, documents);
        updateStatus(examination);

        return true;
    }

    private void updateStatus(Examination examination) {

        if (examination.getExaminationStatus().equals(ExaminationStatus.AllCOMPLETED)) {
            throw new ExaminationNotApproveException(examination.getId().toString());
        }
        if (examination.getExaminationStatus().equals(ExaminationStatus.APPROVE)) {
            ExaminationHistory examinationHistory = examination.updateStatus(ExaminationStatus.BEFORE_REVIEW);
            historySaver(examinationHistory);
        }


    }


    private void saveMethod(List<Test> tests, List<Pic> pics, List<Document> documents) {
        testJpaRepository.saveAll(tests);
        picJpaRepository.saveAll(pics);
        documentJpaRepository.saveAll(documents);
    }

    private List<Document> documentPlace(List<MultipartFile> files, List<DocumentCommand> documentCommandList, List<Long> deleteDocumentIdList, Examination examination) {
        List<Document> documents = new ArrayList<>();

        if (deleteDocumentIdList != null) {
            List<Document> documentList = documentJpaRepository.findAllById(deleteDocumentIdList);
            for (Document document : documentList) {
                deleteFile(document.getDocFilePath());
            }
            documentJpaRepository.deleteAll(documentList);
        }

        List<String> fileLocation = saveFileList(files, examination.getId(), "document");
        for (int i = 0; i < documentCommandList.size(); i++) {

            long size = files.get(i).getSize();

            Document document = Document.place(fileLocation.get(i), documentCommandList.get(i), size);
            examination.addDocumentFile(document);
            documents.add(document);

        }
        return documents;
    }


    private List<Pic> picPlace(List<PicCommand> picCommandList, Examination examination, List<Long> deletePicIdList) {
        List<Pic> pics = new ArrayList<>();
        if (deletePicIdList != null) {
            picJpaRepository.deleteAllByIdIn(deletePicIdList);
        }
        if (picCommandList == null) {
            return pics;
        }
        for (PicCommand picCommand : picCommandList) {
            Pic pic = Pic.place(picCommand);
            pic.addExamination(examination);
            pics.add(pic);
        }
        return pics;
    }

    private List<Test> testPlace(List<TestCommand> testCommandList, Examination examination, List<Long> deleteTestIdList) {
        List<Test> tests = new ArrayList<>();
        if (deleteTestIdList != null) {
            testJpaRepository.deleteAllByIdIn(deleteTestIdList);
        }
        if (testCommandList == null) {
            return tests;
        }
        for (TestCommand testCommand : testCommandList) {
            Test test = Test.place(testCommand);
            test.addExamination(examination);
            tests.add(test);
        }
        return tests;
    }

    private Examination getExaminationById(Long examinationId) {
        return examinationRepositorySupport.findById(examinationId);
    }

    public boolean updateExaminationStatusToApproveById(User principal, Long examinationId) {
        Examination examination = getExaminationById(examinationId);
        ExaminationHistory examinationHistory = examination.updateStatus(ExaminationStatus.APPROVE);
        examinationHistoryJpaRepository.save(examinationHistory);

        return true;
    }

    public boolean documentViewUpdate(User user, Long documentId) {


        Document document = getDocument(documentId);
        applicantCheck(user.getId(), document.getExamination().getApcManager().getId());
        document.updateDocumentViewType(DocumentViewType.AFTERVIEW);
        Examination examination = document.getExamination();
        if (!examination.getExaminationStatus().equals(ExaminationStatus.AFTER_REVIEW)) {
            ExaminationHistory examinationHistory = examination.updateStatus(ExaminationStatus.AFTER_REVIEW);
            examinationHistoryJpaRepository.save(examinationHistory);
        }

        return true;
    }

    private void applicantCheck(UUID userId, UUID documentUserId) {
        if (!userId.equals(documentUserId)) {
            throw new UserMisMatchException(userId.toString());
        }
    }

    private Document getDocument(Long documentId) {
        Optional<Document> documentOptional = documentJpaRepository.findById(documentId);
        if (documentOptional.isEmpty()) {
            throw new DocumentNotFoundException(documentId.toString());
        }
        return documentOptional.get();
    }

    public boolean updateExaminationStatusToCompleteById(User user, Long examinationId) {

        Examination examination = getExaminationById(examinationId);
        applicantCheck(user.getId(), examination.getApcManager().getId());
        documentViewCheck(examination);
        ExaminationHistory examinationHistory = examination.updateStatus(ExaminationStatus.COMPLETED);
        examinationHistoryJpaRepository.save(examinationHistory);
//        documentPathModify(examination.getId(), completeCommand.getCompleteDtoList());

        return true;
    }

    private void documentPathModify(Long examinationId, List<CompleteDto> completeDtoList) {
        for (CompleteDto completeDto : completeDtoList) {
            Document document = examinationRepositorySupport.findDocumentById(completeDto.getDocumentId(), examinationId);
            document.modifyPath(completeDto.getPath());
        }
    }


    private void documentViewCheck(Examination examination) {
        if (!examination.getDocuments()
                .stream().allMatch(e -> e.getDocumentViewType().equals(DocumentViewType.AFTERVIEW))) {
            throw new AllDocumentNotConfirmException(examination.getId().toString());
        }
    }

    public boolean updateExaminationStatusToAllCompleteById(User principal, Long examinationId) {

        Examination examination = getExaminationById(examinationId);
        checkStatus(examination);
        userCheck(principal, examination.getApcManager());
        ExaminationHistory examinationHistory = examination.updateStatus(ExaminationStatus.AllCOMPLETED);
        examinationHistoryJpaRepository.save(examinationHistory);
        return true;
    }

    private void userCheck(User principal, User apcManager) {
        if (!adminCheck(principal) && !principal.getId().equals(apcManager.getId())) {
            throw new ExaminationMisMatchException(principal.getId().toString());
        }
    }

    private void checkStatus(Examination examination) {
        if (!examination.getExaminationStatus().equals(ExaminationStatus.COMPLETED)) {
            throw new ExaminationNotCompleteException(examination.getId().toString());
        }
    }

    public boolean deleteById(User principal, Long examinationId) {

        Examination examination = getExaminationById(examinationId);
        examinationUserCheck(examination.getApcManager().getId(), principal);
        examination.setDeletedDate();
        examinationHistoryJpaRepository.save(ExaminationHistory.create(examination, "delete_examination"));
        return true;
    }

    private void examinationUserCheck(UUID examinationUserId, User principal) {
        if (!examinationUserId.equals(principal.getId()) && !adminCheck(principal)) {
            throw new ExaminationMisMatchException(examinationUserId.toString());
        }
    }

    private boolean adminCheck(User user) {
        return user.getRoles().stream().anyMatch(e -> e.equals("ROLE_ADMIN"));
    }

    public boolean modifyExaminationById(User principal, ExaminationModifyCommand modifyCommand, List<MultipartFile> files) {

        Examination examination = getExaminationById(modifyCommand.getExaminationId());

        examinationApplicantCheck(principal, examination.getApcManager().getId());
        deleteFileList(modifyCommand.getDeleteProReferenceIdList(), examination.getId());
        deleteRequirementList(modifyCommand.getDeleteRequirementeList(), examination.getId());

        SetExaminationCommand setExaminationCommand = new SetExaminationCommand();

        examination.modify(modifyCommand, createSetCommand(setExaminationCommand, modifyCommand));

        requirementPlace(modifyCommand.getRequirements(), examination);

        historySaver(ExaminationHistory.create(examination, "modify_examination"));
        createProReferenceFiles(files, examination);//파일 있으면
        return true;
    }

    private void requirementPlace(List<RequirementCommand> requirements, Examination examination) {
        if (requirements != null) {
            List<Requirement> requirementList = new ArrayList<>();
            for (RequirementCommand requirementCommand : requirements) {
                requirementList.add(Requirement.create(requirementCommand.getReqContent(), examination));
            }
            examination.addRequirementList(requirementList);
        }
    }

    private SetExaminationCommand createSetCommand(SetExaminationCommand setExaminationCommand, ExaminationModifyCommand modifyCommand) {
        User applicant = getUser(modifyCommand.getApplicantId());
        setExaminationCommand.setApplicant(applicant);//컴퍼니
//        Company mftCompany = getCompany(modifyCommand.getMftCompanyId());
        SubCompany mftCompany = getSubCompany(modifyCommand.getMftCompanyId());
        setExaminationCommand.setMftCompany(mftCompany);
//        Company facCompany = getCompany(modifyCommand.getFacCompanyId());
        SubCompany facCompany = getSubCompany(modifyCommand.getFacCompanyId());
        setExaminationCommand.setFacCompany(facCompany);
        return setExaminationCommand;
    }

    private void deleteRequirementList(List<Long> deleteRequirementeIdList, Long examinationId) {
        if (deleteRequirementeIdList != null) {

            List<Requirement> requirementList = requirementJpaRepository.findAllById(deleteRequirementeIdList);

            requirementJpaRepository.deleteAll(requirementList);
        }
    }

    private void examinationApplicantCheck(User user, UUID examinationUserId) {
        if (!user.getId().equals(examinationUserId) && !adminCheck(user)) {
            throw new UserMisMatchException(examinationUserId.toString());
        }
    }

    private void deleteFileList(List<Long> deleteProReferenceIdList, Long examinationId) {
        if (deleteProReferenceIdList != null) {
            List<ProReferencefile> proReferencefileList = proReferenceFileJpaRepository.findAllById(deleteProReferenceIdList);

            for (ProReferencefile proReferencefile : proReferencefileList) {
                deleteFile(proReferencefile.getPath());
            }
            proReferenceFileJpaRepository.deleteAll(proReferencefileList);
        }
    }

    private void examinationMatchCheck(List<Examination> examinationList, Long examinationId) {
        boolean result = examinationList.stream().map(Examination::getId).allMatch(e -> e.equals(examinationId));
        if (result) {
            throw new ProReferenceFileMisMatchException(examinationId.toString());
        }

    }


    public void historySaver(ExaminationHistory examinationHistory) {
        examinationHistoryJpaRepository.save(examinationHistory);
    }


    public boolean updateDocumentById(User principal, DocumentUpdateCommand documentCommandList) {

        if (documentCommandList.getDeleteDocumentIdList() != null) {
            List<Document> documents = examinationRepositorySupport.findAllByDocumentId(documentCommandList.getDeleteDocumentIdList());
            documentJpaRepository.deleteAll(documents);
            //deleteIdList remove

            Examination examination = getExaminationById(documentCommandList.getExaminationId());
            List<Document> documentList = new ArrayList<>();
            for (DocumentCommand documentCommand : documentCommandList.getDocumentCommandList()) {
                Document document = Document.place(documentCommand.getDocFilePath(), documentCommand, 0);
                document.addExamination(examination);
                documentList.add(document);
            }
            documentJpaRepository.saveAll(documentList);
            //docummentCreate

            historySaver(ExaminationHistory.create(examination, "QR_UPDATE"));

            return true;
        } else {
            return false;
        }
    }
}
