package com.laonstory.poc_be_spring.domain.examination.application;

import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanySubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.dto.response.SubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.persistance.SubCompanyRepositorySupport;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.examination.dto.response.ExaminationPageResponse;
import com.laonstory.poc_be_spring.domain.examination.dto.response.ExaminationResponse;
import com.laonstory.poc_be_spring.domain.examination.dto.response.HistoryPageResponse;
import com.laonstory.poc_be_spring.domain.examination.exception.DIDNotFoundException;
import com.laonstory.poc_be_spring.domain.examination.persistance.ExaminationRepositorySupport;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.persistance.UserRepositorySupport;
import com.laonstory.poc_be_spring.global.application.CustomDateService;
import com.laonstory.poc_be_spring.global.dto.DateDTO;
import com.laonstory.poc_be_spring.global.dto.request.PageRequest;
import com.laonstory.poc_be_spring.global.dto.response.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ExaminationQueryService {


    private final ExaminationRepositorySupport examinationRepositorySupport;
    private final CustomDateService customDateService;
    private final UserRepositorySupport userRepositorySupport;
    private final SubCompanyRepositorySupport subCompanyRepositorySupport;




    public PagingResponse<ExaminationPageResponse> findAll(User principal, int page, String query) {
        PageRequest pageRequest = new PageRequest(page, 10);

        Page<ExaminationPageResponse> examinationPage = examinationRepositorySupport.findAll(pageRequest.of(), query);
        return new PagingResponse<>(examinationPage);

    }


    public PagingResponse<ExaminationPageResponse> findAllByCondition(User principal, int page,
                                                                      String condition, String query,
                                                                      ExaminationStatus status,
                                                                      String startDate, String endDate) {

        didCheck(principal);
        PageRequest pageRequest = new PageRequest(page, 10);
        DateDTO dateDTO = new DateDTO();
        dateDTO = customDate(startDate, endDate, dateDTO);


        Page<ExaminationPageResponse> examinationPage = examinationRepositorySupport.findAllByCondition(pageRequest.of(), condition, query, principal.getId(), status, dateDTO);
        return new PagingResponse<>(examinationPage);

    }
    private void didCheck(User principal) {
        String DID=getUserById(principal.getId()).getDidAddress();
        if (DID==null ||DID.isBlank()){
            throw new DIDNotFoundException(principal.getId().toString());
        }

    }

    private User getUserById(UUID applicantId) {
        return userRepositorySupport.findById(applicantId);
    }

    public ExaminationResponse findById(User principal, Long examinationId) {
        Examination examination = examinationRepositorySupport.findById(examinationId);
        return new ExaminationResponse(examination);


    }

    public PagingResponse<ExaminationPageResponse> findAllByConditionManager(User principal, int page,
                                                                             String condition, String query,
                                                                             ExaminationStatus status,
                                                                             String startDate, String endDate) {
        PageRequest pageRequest = new PageRequest(page, 10);
        DateDTO dateDTO = new DateDTO();
        dateDTO = customDate(startDate, endDate, dateDTO);

        Page<ExaminationPageResponse> examinationPage =
                examinationRepositorySupport.findAllByConditionManager(pageRequest.of(), condition, query, status, dateDTO);
        return new PagingResponse<>(examinationPage);

    }

    private DateDTO customDate(String startDate, String endDate, DateDTO dateDTO) {

        if (startDate == null && endDate == null) {
            dateDTO.setNull();

            return dateDTO;
        }
        dateDTO = customDateService.dateConvert(startDate, endDate, dateDTO);
        return dateDTO;
    }

    public PagingResponse<HistoryPageResponse> findAllHistoryById(int page, Long examinationId, String startDate, String endDate) {
        PageRequest pageRequest = new PageRequest(page, 10);
        DateDTO  dateDTO = new DateDTO();
        dateDTO = customDate(startDate,endDate,dateDTO);
        Page<HistoryPageResponse> historyPage = examinationRepositorySupport.findHistoryById(pageRequest.of(), examinationId,dateDTO);
        return new PagingResponse<>(historyPage);

    }

//    public PagingResponse<SubCompanyResponse> findSubCompanyAllByCompanyId(int page, User principal, CompanyType companyType,String query) {
//        PageRequest pageRequest =new PageRequest(page,10);
//
//        User user=getUserById(principal.getId());
//
//
//        Page<SubCompanyResponse> subCompanyPage=subCompanyRepositorySupport.findSubCompanyPageByCompanyId(pageRequest.of(),user.getCompany().getId(),companyType,query);
//        return new PagingResponse<>(subCompanyPage);
//
//    }
//    private boolean adminCheck(User user) {
//        return user.getRoles().stream().anyMatch(e -> e.equals("ROLE_ADMIN"));
//    }
//    public PagingResponse<CompanySubCompanyResponse> manageFindSubCompanyAllByCompanyId(int page, User principal, CompanyType companyType,String query) {
//
//        PageRequest pageRequest =new PageRequest(page,10);
//
//        Page<CompanySubCompanyResponse> subCompanyPage=subCompanyRepositorySupport.manageFindSubCompanyByCompanyId(pageRequest.of(),companyType,query);
//        return new PagingResponse<>(subCompanyPage);
//
//    }
}

