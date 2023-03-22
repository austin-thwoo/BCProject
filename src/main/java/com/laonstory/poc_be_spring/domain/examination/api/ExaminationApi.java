package com.laonstory.poc_be_spring.domain.examination.api;

import com.laonstory.poc_be_spring.domain.examination.application.ExaminationCommandService;

import com.laonstory.poc_be_spring.domain.examination.application.ExaminationQueryService;
import com.laonstory.poc_be_spring.domain.examination.common.ExaminationStatus;
import com.laonstory.poc_be_spring.domain.examination.dto.request.ExaminationCommand;
import com.laonstory.poc_be_spring.domain.examination.dto.request.ExaminationModifyCommand;
import com.laonstory.poc_be_spring.domain.examination.dto.response.ExaminationPageResponse;
import com.laonstory.poc_be_spring.domain.examination.dto.response.ExaminationResponse;
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
@RequestMapping("/examination")
public class ExaminationApi {


    private final ExaminationQueryService examinationQueryService;
    private final ExaminationCommandService examinationCommandService;


    @ApiOperation(value = "시험 신청")
    @PostMapping
    public ApiResponse<Boolean> save(@AuthenticationPrincipal User principal,
                                     @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) ExaminationCommand examinationCommand,
                                     @RequestPart(name = "files", required = false) List<MultipartFile> files) {
        return new ApiResponse<>(examinationCommandService.save(principal, examinationCommand, files));
    }


    @ApiOperation(value = "시험 목록")
    @GetMapping
    public PagingResponse<ExaminationPageResponse> findAll(@AuthenticationPrincipal User principal,
                                                           @RequestParam(required = false, defaultValue = "1") int page,
                                                           @RequestParam(required = false) String query) {
        return examinationQueryService.findAll(principal, page, query);
    }

    @ApiOperation(value = "시험 상세")
    @GetMapping("/{examinationId}")
    public ApiResponse<ExaminationResponse> findById(@AuthenticationPrincipal User principal,
                                                     @PathVariable(name = "examinationId") Long examinationId) {
        return new ApiResponse<>(examinationQueryService.findById(principal, examinationId));
    }

    @ApiOperation(value = "시험 삭제")
    @DeleteMapping("/{examinationId}")
    public ApiResponse<Boolean> deleteById(@AuthenticationPrincipal User principal,
                                           @PathVariable(name = "examinationId") Long examinationId) {
        return new ApiResponse<>(examinationCommandService.deleteById(principal, examinationId));

    }

    @ApiOperation(value = "시험 수정")
    @PatchMapping("/modify")
    public ApiResponse<Boolean> modifyById(@AuthenticationPrincipal User principal,
                                           @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) ExaminationModifyCommand modifyCommand,
                                           @RequestPart List<MultipartFile> files


    ) {
        return new ApiResponse<>(examinationCommandService.modifyExaminationById(principal, modifyCommand, files));
    }


    @ApiOperation(value = "페이지 별 현황")
    @GetMapping("/condition")
    public PagingResponse<ExaminationPageResponse> findAllByCondition(@AuthenticationPrincipal User principal,
                                                                      @RequestParam(required = false, defaultValue = "1") int page,
                                                                      @RequestParam(required = false) String condition,
                                                                      @RequestParam(required = false) String query,
                                                                      @RequestParam(required = false) ExaminationStatus status,
                                                                      @RequestParam(required = false) String startDate,
                                                                      @RequestParam(required = false) String endDate) {
        return examinationQueryService.findAllByCondition(principal, page, condition, query, status, startDate, endDate);
    }

    @ApiOperation(value = "serialCodeTest")
    @GetMapping("/test")
    public void test() {
        examinationCommandService.createSerialCode();
    }

    @ApiOperation("고객검토 버튼")
    @PatchMapping("/documentview/{documentId}")
    public ApiResponse<Boolean> documentViewUpdate(@AuthenticationPrincipal User user,
                                                   @PathVariable(name = "documentId") Long documentId) {
        return new ApiResponse<>(examinationCommandService.documentViewUpdate(user, documentId));
    }

    @ApiOperation("검토완료 버튼")
    @PatchMapping("/complete/{examinationId}")
    public ApiResponse<Boolean> examinationUpdate(@AuthenticationPrincipal User user,
                                                  @PathVariable(name = "examinationId") Long examinationId) {
        return new ApiResponse<>(examinationCommandService.updateExaminationStatusToCompleteById(user, examinationId));
    }

//    @ApiOperation("제조사/공장찾기")
//    @GetMapping("/subcompany")
//    public PagingResponse<SubCompanyResponse> findByCompanyId(@AuthenticationPrincipal User principal,
//                                                              @RequestParam(required = false, defaultValue = "1") int page,
//                                                              @RequestParam(required = false)String query,
//                                                              @RequestParam(required = false) CompanyType companyType){
//        return examinationQueryService.findSubCompanyAllByCompanyId(page,principal,companyType,query);
//    }

}
