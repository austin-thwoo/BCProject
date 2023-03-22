package com.laonstory.poc_be_spring.domain.user.api;

import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.company.dto.request.SubCompanyCommand;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanyResponse;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanySubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.dto.response.SubCompanyResponse;
import com.laonstory.poc_be_spring.domain.examination.dto.request.UserDidCommand;
import com.laonstory.poc_be_spring.domain.user.application.UserCommandService;
import com.laonstory.poc_be_spring.domain.user.application.UserQueryService;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.request.ChangePasswordCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.CompanyRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.UserRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.dto.response.SimpleUserResponse;
import com.laonstory.poc_be_spring.domain.user.dto.response.TokenResponse;
import com.laonstory.poc_be_spring.domain.user.dto.response.UserResponse;
import com.laonstory.poc_be_spring.global.dto.response.ApiListResponse;
import com.laonstory.poc_be_spring.global.dto.response.ApiResponse;
import com.laonstory.poc_be_spring.global.dto.response.PagingResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApi {


    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @ApiOperation(value = "회사 등록")
    @PostMapping("/company")
    public ApiResponse<CompanyResponse> save(@RequestBody CompanyRegisterCommand companyRegisterCommand) {
        return new ApiResponse<>(userCommandService.save(companyRegisterCommand));

    }

    @ApiOperation(value = "유저 제조사/공장 등록")
    @PostMapping("/subcompany")
    public ApiResponse<SubCompanyResponse> saveSubCompany(@AuthenticationPrincipal User principal,
                                                          @RequestBody SubCompanyCommand companyCommand) {
        return new ApiResponse<>(userCommandService.saveSubCompany(principal, companyCommand));
    }

    @ApiOperation(value = "유저 제조사/공장 정보수정")
    @PatchMapping("/subcompany")
    public ApiResponse<SubCompanyResponse> modifySubCompany(@AuthenticationPrincipal User principal,
                                                            @RequestBody SubCompanyCommand subCompanyCommand) {
        return new ApiResponse<>(userCommandService.modifySubCompany(principal, subCompanyCommand));
    }

    @ApiOperation("제조사/공장 상세보기")
    @GetMapping("/subcompany/{subCompanyId}")
    public ApiResponse<CompanySubCompanyResponse> findSubCompanyById(@AuthenticationPrincipal User principal,
                                                                     @PathVariable(name = "subCompanyId") Long subCompanyId) {
        return new ApiResponse<>(userQueryService.findSubCompanyById(principal, subCompanyId));

    }
    @ApiOperation("유저 내 제조사 공장 목록보기")
    @GetMapping("/subcompany")
    public PagingResponse<CompanySubCompanyResponse> findAllSubCompanyByMe(@AuthenticationPrincipal User principal,
                                                                           @RequestParam(name = "page",required = false,defaultValue = "1")int page,
                                                                           @RequestParam(name = "query", required = false) String query,
                                                                           @RequestParam(required = false) CompanyType companyType){
        return new PagingResponse<>(userQueryService.findAllSubCompany(principal,page, query,companyType));
    }
    @ApiOperation("유저 내 제조사 공장 삭제하기")
    @DeleteMapping("/subcompany/{subCompanyId}")
    public ApiResponse<SubCompanyResponse> deleteSubCompanyById(@AuthenticationPrincipal User principal,
                                                                @PathVariable(name = "subCompanyId")Long subCompanyId){
        return new ApiResponse<>(userCommandService.deleteSubCompanyBySubCompanyId(principal,subCompanyId));

    }

    @ApiOperation("유저 내 제조사 공장 복구하기")
    @PatchMapping("/subcompany/{subCompanyId}")
    public ApiResponse<SubCompanyResponse> deleteSubCompanyBySubCompanyId(@AuthenticationPrincipal User principal,
                                                                @PathVariable(name = "subCompanyId")Long subCompanyId){
        return new ApiResponse<>(userCommandService.unDeleteSubCompanyBySubCompanyId(principal,subCompanyId));

    }
    @ApiOperation(value = "내정보")
    @GetMapping("/me")
    public ApiResponse<TokenResponse> findMe(@AuthenticationPrincipal User principal) {
        return new ApiResponse<>(userQueryService.findMe(principal));
    }


    @ApiOperation(value = "내정보 수정")
    @PatchMapping("/me")
    public ApiResponse<UserResponse> updateMe(@AuthenticationPrincipal User principal,
                                              @RequestBody UserRegisterCommand command) {
        return new ApiResponse<>(userCommandService.updateMe(principal, command));
    }

    @ApiOperation(value = "비밀번호 변경")
    @PatchMapping("/password")
    public ApiResponse<Boolean> changePassword(@AuthenticationPrincipal User principal,
                                               @Valid @RequestBody ChangePasswordCommand changePasswordCommand) {
        return new ApiResponse<>(userCommandService.changePassword(principal, changePasswordCommand));
    }

    @ApiOperation(value = "회사 하위 유저 목록")
    @GetMapping("/user/{companyId}")
    public ApiListResponse<SimpleUserResponse> findUserByCompanyId(@AuthenticationPrincipal User principal,
                                                                   @PathVariable(name = "companyId") Long companyId) {
        return new ApiListResponse<>(userQueryService.findUserByCompanyId(principal, companyId));
    }

    @ApiOperation(value = "DID 주소 업데이트")
    @PatchMapping("/didaddress")
    public ApiResponse<Boolean> test(@RequestBody UserDidCommand didCommand) {
        return new ApiResponse<>(userCommandService.updateUserDid(didCommand));
    }


}