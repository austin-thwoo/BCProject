package com.laonstory.poc_be_spring.domain.standard.api;

import com.laonstory.poc_be_spring.domain.standard.application.StandardCommandService;
import com.laonstory.poc_be_spring.domain.standard.application.StandardQueryService;
import com.laonstory.poc_be_spring.domain.standard.dto.request.StandardCommand;
import com.laonstory.poc_be_spring.domain.standard.dto.request.StandardDeleteCommand;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdCertifyResponse;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdLineResponse;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdMachineResponse;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdNationResponse;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.global.dto.response.ApiListResponse;
import com.laonstory.poc_be_spring.global.dto.response.ApiResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/standard")
public class StandardApi {

    private final StandardCommandService standardCommandService;
    private final StandardQueryService standardQueryService;

    /*@PreAuthorize("hasRole('ROLE_ADMIN')")*/
    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "국가분류 등록")
    @PostMapping("/nation")
    public ApiResponse<Boolean> saveNation(@AuthenticationPrincipal User principal,
                                           @RequestBody StandardCommand standardCommand) {
        return new ApiResponse<>(standardCommandService.saveNation(principal, standardCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "인증분류 등록")
    @PostMapping("/certify")
    public ApiResponse<Boolean> saveCertify(@AuthenticationPrincipal User principal,
                                            @RequestBody StandardCommand standardCommand) {
        return new ApiResponse<>(standardCommandService.saveCertify(principal, standardCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "기기분류 등록")
    @PostMapping("/machine")
    public ApiResponse<Boolean> saveMachine(@AuthenticationPrincipal User principal,
                                            @RequestBody StandardCommand standardCommand) {
        return new ApiResponse<>(standardCommandService.saveMachine(principal, standardCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "분야분류 등록")
    @PostMapping("/line")
    public ApiResponse<Boolean> saveLine(@AuthenticationPrincipal User principal,
                                         @RequestBody StandardCommand standardCommand) {
        return new ApiResponse<>(standardCommandService.saveLine(principal, standardCommand));
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "국가분류 수정")
    @PatchMapping("/nation")
    public ApiResponse<Boolean> updateNation(@AuthenticationPrincipal User principal,
                                             @RequestBody StandardCommand standardCommand) {
        return new ApiResponse<>(standardCommandService.updateNation(principal, standardCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "인증분류 수정")
    @PatchMapping("/certify")
    public ApiResponse<Boolean> updateCertify(@AuthenticationPrincipal User principal,
                                              @RequestBody StandardCommand standardCommand) {
        return new ApiResponse<>(standardCommandService.updateCertify(principal, standardCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "기기분류 수정")
    @PatchMapping("/machine")
    public ApiResponse<Boolean> updateMachine(@AuthenticationPrincipal User principal,
                                              @RequestBody StandardCommand standardCommand) {
        return new ApiResponse<>(standardCommandService.updateMachine(principal, standardCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "분야분류 수정")
    @PatchMapping("/line")
    public ApiResponse<Boolean> updateLine(@AuthenticationPrincipal User principal,
                                           @RequestBody StandardCommand standardCommand) {
        return new ApiResponse<>(standardCommandService.updateLine(principal, standardCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "국가분류 삭제")
    @DeleteMapping("/nation")
    public ApiResponse<Boolean> deleteNation(@AuthenticationPrincipal User principal,
                                             @RequestBody StandardDeleteCommand deleteCommand) {
        return new ApiResponse<>(standardCommandService.deleteNation(principal, deleteCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "인증분류 삭제")
    @DeleteMapping("/certify")
    public ApiResponse<Boolean> deleteCertify(@AuthenticationPrincipal User principal,
                                              @RequestBody StandardDeleteCommand deleteCommand) {
        return new ApiResponse<>(standardCommandService.deleteCertify(principal, deleteCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "기기분류 삭제")
    @DeleteMapping("/machine")
    public ApiResponse<Boolean> deleteMachine(@AuthenticationPrincipal User principal,
                                              @RequestBody StandardDeleteCommand deleteCommand) {
        return new ApiResponse<>(standardCommandService.deleteMachine(principal, deleteCommand));

    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "분야분류 삭제")
    @DeleteMapping("/line")
    public ApiResponse<Boolean> deleteLine(@AuthenticationPrincipal User principal,
                                           @RequestBody StandardDeleteCommand deleteCommand) {
        return new ApiResponse<>(standardCommandService.deleteLine(principal, deleteCommand));

    }

    @Secured("ROLE_USER")
    @ApiOperation(value = "국가분류 목록")
    @GetMapping("/nation")
    public ApiListResponse<StdNationResponse> Nation(@AuthenticationPrincipal User principal,
                                                     @RequestParam(required = false) String name) {
        return new ApiListResponse<>(standardQueryService.findNationAll(principal, name));

    }

    @Secured("ROLE_USER")
    @ApiOperation(value = "인증분류 목록")
    @GetMapping("/certify/{stdNationId}")
    public ApiListResponse<StdCertifyResponse> Certify(@AuthenticationPrincipal User principal,
                                                       @PathVariable(name = "stdNationId") Long stdNationId,
                                                       @RequestParam(required = false) String name) {
        return new ApiListResponse<>(standardQueryService.findCertifyAll(principal, stdNationId, name));

    }


    @Secured("ROLE_USER")
    @ApiOperation(value = "기기분류 목록")
    @GetMapping("/machine/{stdCertifyId}")
    public ApiListResponse<StdMachineResponse> Machine(@AuthenticationPrincipal User principal,
                                                       @PathVariable(name = "stdCertifyId") Long stdCertifyId,
                                                       @RequestParam(required = false) String name) {
        return new ApiListResponse<>(standardQueryService.findMachineAll(principal, stdCertifyId, name));

    }

    @Secured("ROLE_USER")
    @ApiOperation(value = "분야분류 목록")
    @GetMapping("/line/{stdMachineId}")
    public ApiListResponse<StdLineResponse> Line(@AuthenticationPrincipal User principal,
                                                 @PathVariable(name = "stdMachineId") Long stdMachineId,
                                                 @RequestParam(required = false) String name) {
        return new ApiListResponse<>(standardQueryService.findLineAll(principal, stdMachineId, name));

    }

}