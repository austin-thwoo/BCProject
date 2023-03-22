package com.laonstory.poc_be_spring.domain.user.api;

import com.laonstory.poc_be_spring.domain.company.dto.response.CompanyResponse;
import com.laonstory.poc_be_spring.domain.user.application.AuthService;
import com.laonstory.poc_be_spring.domain.user.application.LoginService;
import com.laonstory.poc_be_spring.domain.user.application.RegisterService;
import com.laonstory.poc_be_spring.domain.user.dto.request.FindPasswordCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.LoginCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.UserRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.dto.response.SimpleUserResponse;
import com.laonstory.poc_be_spring.domain.user.dto.response.TokenResponse;
import com.laonstory.poc_be_spring.global.dto.response.ApiPagingResponse;
import com.laonstory.poc_be_spring.global.dto.response.ApiResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequiredArgsConstructor
@Api(value = "미인증 사용자")
@RequestMapping("/auth")
public class AuthApi {

    private final AuthService authService;
    private final RegisterService registerService;
    private final LoginService loginService;

    @ApiOperation(value = "회사 목록")
    @GetMapping("/company")
    public ApiPagingResponse<CompanyResponse> findCompanyAll(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                             @RequestParam(name = "query", required = false) String query) {
        return new ApiPagingResponse<>(authService.findCompanyAll(page, query));
    };

    @ApiOperation(value = "회원가입")
    @PostMapping
    public ApiResponse<TokenResponse> save(@RequestBody UserRegisterCommand registerCommand) {
        return new ApiResponse<>(registerService.register(registerCommand));
    }

    @ApiOperation(value = "로그인", notes = "로그인->토큰발행")
    @PostMapping("/login")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "로그인을 성공했습니다."),
            @io.swagger.annotations.ApiResponse(code = 404, message = "고객 아이디로 정보를 조회할 수 없습니다.\n삭제되거나 없는 고객입니다.")
    })
    public ApiResponse<TokenResponse> login(@RequestBody LoginCommand loginCommand) {
        return new ApiResponse<>(loginService.login(loginCommand));
    }

    @ApiOperation(value = "아이디 중복확인 버튼")
    @GetMapping("/overlap")
    public ApiResponse<Boolean> usernameOverLap(@RequestParam String userName) {
        return new ApiResponse<>(authService.usernameOverLap(userName));

    }

    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userName",  value = "ID", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "name", value = "유저 이름", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "userPhone", value = "유저 전화번호", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "form")})
    @ApiOperation(value = "비밀번호 찾기1")
    @PostMapping("/password/code")
    public ApiResponse<SimpleUserResponse> sendPasswordCode(@RequestBody FindPasswordCommand findPasswordCommand) {
        return new ApiResponse<>(authService.sendCode(findPasswordCommand));
    }

    @ApiOperation(value = "비밀번호 찾기2")
    @PatchMapping("/password")
    public ApiResponse<Boolean> sendPassword(@RequestBody FindPasswordCommand findPasswordCommand) {
        return new ApiResponse<>(authService.sendPassword(findPasswordCommand));

    }

    @ApiOperation(value = "이메일인증 신청")
    @PostMapping("/email/confirm")
    public ApiResponse<Boolean> emailConfirm(@RequestParam String userEmail) {
        return new ApiResponse<>(registerService.emailConfirm(userEmail));
    }

    @ApiOperation(value = "인증 코드확인")
    @GetMapping("/email/passcode")
    private ApiResponse<String> passCode(@RequestParam String code,
                                         @RequestParam String userEmail) {
        return new ApiResponse<>(registerService.passCode(code, userEmail));
    }
}
