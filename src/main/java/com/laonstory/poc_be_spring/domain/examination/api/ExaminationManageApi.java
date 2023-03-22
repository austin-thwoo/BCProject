package com.laonstory.poc_be_spring.domain.examination.api;

import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanySubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.dto.response.SubCompanyResponse;
import com.laonstory.poc_be_spring.domain.examination.application.ExaminationCommandService;
import com.laonstory.poc_be_spring.domain.examination.application.ExaminationQueryService;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.dto.request.DocumentUpdateCommand;
import com.laonstory.poc_be_spring.domain.examination.dto.request.ExaminationUpdateCommand;
import com.laonstory.poc_be_spring.domain.examination.dto.response.ExaminationPageResponse;
import com.laonstory.poc_be_spring.domain.examination.dto.response.HistoryPageResponse;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.global.dto.response.ApiResponse;
import com.laonstory.poc_be_spring.global.dto.response.PagingResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/examination")
public class ExaminationManageApi {


    private final ExaminationQueryService examinationQueryService;
    private final ExaminationCommandService examinationCommandService;



    @ApiOperation(value = "시험 추가 사항")
    @PatchMapping("/{examinationId}")
    public ApiResponse<Boolean> updateById(@AuthenticationPrincipal User principal,
                                           @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) ExaminationUpdateCommand updateCommand,
                                           @RequestPart(name = "files", required = false) List<MultipartFile> files) {
        return new ApiResponse<>(examinationCommandService.addExamination(principal, updateCommand, files));
    }

    @ApiOperation(value = "createQR")
    @PatchMapping("/QR/{examinationId}")
    public ApiResponse<Boolean> updateQRById(@AuthenticationPrincipal User principal,
                                             @RequestBody DocumentUpdateCommand documentUpdateCommand) {
        return new ApiResponse<>(examinationCommandService.updateDocumentById(principal,documentUpdateCommand));
    }

    @ApiOperation(value = "접수 승인")
    @PatchMapping("/approve/{examinationId}")
    public ApiResponse<Boolean> updateApproveById(@AuthenticationPrincipal User principal,
                                                  @PathVariable(name = "examinationId") Long examinationId) {
        return new ApiResponse<>(examinationCommandService.updateExaminationStatusToApproveById(principal, examinationId));
    }

    @ApiOperation(value = "최종 승인")
    @PatchMapping("/allcomplete/{examinationId}")
    public ApiResponse<Boolean> updateAllCompleteById(@AuthenticationPrincipal User principal,
                                                      @PathVariable(name = "examinationId") Long examinationId) {
        return new ApiResponse<>(examinationCommandService.updateExaminationStatusToAllCompleteById(principal, examinationId));
    }

    @ApiOperation(value = "페이지 별 현황")
    @GetMapping("/condition")
    public PagingResponse<ExaminationPageResponse> findAllByCondition(@AuthenticationPrincipal User principal,
                                                                      @RequestParam(required = false, defaultValue = "1") int page,
                                                                      @RequestParam(required = false) String condition,
                                                                      @RequestParam(required = false) ExaminationStatus status,
                                                                      @RequestParam(required = false) String query,
                                                                      @RequestParam(required = false) String startDate,
                                                                      @RequestParam(required = false) String endDate) {
        return examinationQueryService.findAllByConditionManager(principal, page, condition, query, status, startDate, endDate);
    }

    @ApiOperation(value = "시험 기록")
    @GetMapping("/history")
    public PagingResponse<HistoryPageResponse> findAllHistoryById(@AuthenticationPrincipal User principal,
                                                                  @RequestParam(required = false, defaultValue = "1") int page,
                                                                  @RequestParam(required = false) Long examinationId,
                                                                  @RequestParam(required = false) String startDate,
                                                                  @RequestParam(required = false) String endDate){
        return examinationQueryService.findAllHistoryById(page,examinationId,startDate,endDate);
    }
//    @ApiOperation("제조사/공장찾기")
//    @GetMapping("/subcompany")
//    public PagingResponse<CompanySubCompanyResponse> findByCompanyId(@AuthenticationPrincipal User principal,
//                                                                     @RequestParam(required = false, defaultValue = "1") int page,
//                                                                     @RequestParam(required = false)String query,
//                                                                     @RequestParam(required = false) CompanyType companyType){
//        return examinationQueryService.manageFindSubCompanyAllByCompanyId(page,principal,companyType,query);
//    }


}
