package com.laonstory.poc_be_spring.domain.user.api;

import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.company.dto.request.SubCompanyCommand;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanyResponse;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanySubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.dto.response.SubCompanyResponse;
import com.laonstory.poc_be_spring.domain.user.application.ManagerCommandService;
import com.laonstory.poc_be_spring.domain.user.application.ManagerQueryService;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.request.CompanyRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.UserRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.dto.response.UserResponse;
import com.laonstory.poc_be_spring.global.dto.response.ApiListResponse;
import com.laonstory.poc_be_spring.global.dto.response.ApiResponse;
import com.laonstory.poc_be_spring.global.dto.response.PagingResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage")
public class ManageApi {

    private final ManagerCommandService managerCommandService;
    private final ManagerQueryService managerQueryService;

    @ApiOperation(value = "회사 수정")
    @PatchMapping("/company/{companyId}")
    public ApiResponse<CompanyResponse> updateComapany(@RequestBody CompanyRegisterCommand registerCommand) {
        return new ApiResponse<>(managerCommandService.update(registerCommand));
    }

    @ApiOperation(value = "회사 상세")
    @GetMapping("/company/{companyId}")
    public ApiResponse<CompanyResponse> findCompanyById(@PathVariable(name = "companyId") Long companyId) {
        return new ApiResponse<>(managerCommandService.findCompanyById(companyId));
    }

    @ApiOperation(value = "회사 삭제")
    @DeleteMapping("/company/{companyId}")
    public ApiResponse<CompanyResponse> deleteCompanyById(@PathVariable(name = "companyId") Long companyId) {
        return new ApiResponse<>(managerCommandService.deleteCompanyById(companyId));
    }

    @ApiOperation(value = "회사 복구")
    @PatchMapping("/company/undelete/{companyId}")
    public ApiResponse<Boolean> updateComapany(@PathVariable(name = "companyId") Long companyId) {
        return new ApiResponse<>(managerCommandService.unDeleteCompanyById(companyId));
    }

    @ApiOperation(value = "유저 목록")
    @GetMapping("/user")
    public ApiListResponse<UserResponse> findUserAll(@AuthenticationPrincipal User user,
                                                     @RequestParam(required = false) String userType,
                                                     @RequestParam(required = false) String query,
                                                     @RequestParam(required = false) String startDate,
                                                     @RequestParam(required = false) String endDate) {
        return managerQueryService.findUserAll(userType, query, startDate, endDate, user);
    }

    @ApiOperation(value = "유저 상세")
    @GetMapping("/user/{userId}")
    public ApiResponse<UserResponse> findUserById(@PathVariable(name = "userId") UUID userId) {
        return new ApiResponse<>(managerQueryService.findUserById(userId));
    }

    @ApiOperation(value = "유저 수정")
    @PatchMapping("/user/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable(name = "userId") UUID userId,
                                                @RequestBody UserRegisterCommand command) {
        return new ApiResponse<>(managerCommandService.updateUser(userId, command));
    }

    @ApiOperation(value = "유저 삭제")
    @DeleteMapping("/user/{userId}")
    public ApiResponse<Boolean> deleteUser(@PathVariable(name = "userId") UUID userId) {
        return new ApiResponse<>(managerCommandService.deleteUser(userId));
    }

    @ApiOperation(value = "유저 뽁구")
    @PatchMapping("/user/undelete/{userId}")
    public ApiResponse<Boolean> unDeleteUser(@PathVariable(name = "userId") UUID userId) {
        return new ApiResponse<>(managerCommandService.unDeleteUser(userId));
    }

    @ApiOperation(value = "가입 승인")
    @PostMapping("/user/{userId}")
    public ApiResponse<Boolean> approveUser(@PathVariable UUID userId,
                                            @AuthenticationPrincipal User principal) {
        return new ApiResponse<>(managerCommandService.approveUser(principal, userId));
    }

    @ApiOperation(value = "매니저 제조사/공장 등록")
    @PostMapping("/subcompany")
    public ApiResponse<SubCompanyResponse> saveSubCompany(@AuthenticationPrincipal User principal,
                                                          @RequestBody SubCompanyCommand subCompanyCommand) {
        return new ApiResponse<>(managerCommandService.saveSubCompany(principal, subCompanyCommand));
    }


    @ApiOperation("매니저 전체 제조사/공장 목록보기")
    @GetMapping("/subcompany")
    public PagingResponse<CompanySubCompanyResponse> findAllSubCompanyByCompanyId(@AuthenticationPrincipal User principal,
                                                                                  @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                                                  @RequestParam(name = "query", required = false) String query,
                                                                                  @RequestParam(name = "companyId",required = false) Long companyId,
                                                                                  @RequestParam(required = false) CompanyType companyType) {
        return new PagingResponse<>(managerQueryService.findAllSubCompany(principal, page, query, companyId,companyType));

    }



}