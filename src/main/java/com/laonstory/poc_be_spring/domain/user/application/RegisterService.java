package com.laonstory.poc_be_spring.domain.user.application;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.persistance.CompanyRepositorySupport;
import com.laonstory.poc_be_spring.domain.user.domain.EmailConfirm;
import com.laonstory.poc_be_spring.domain.user.domain.PassCode;
import com.laonstory.poc_be_spring.domain.user.dto.request.MailDTO;
import com.laonstory.poc_be_spring.domain.user.dto.request.UserRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.request.TokenDTO;
import com.laonstory.poc_be_spring.domain.user.dto.response.TokenResponse;
import com.laonstory.poc_be_spring.domain.user.exception.AuthentificationMisMatchException;
import com.laonstory.poc_be_spring.domain.user.exception.PassCodeMisMatchException;
import com.laonstory.poc_be_spring.domain.user.exception.PassCodeTimeOverException;
import com.laonstory.poc_be_spring.domain.user.exception.UserEmailDuplicatedException;
import com.laonstory.poc_be_spring.domain.user.persistance.*;
import com.laonstory.poc_be_spring.global.provider.TokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RegisterService {

    private final EmailConfirmRepositorySupport emailConfirmRepositorySupport;
    private final TokenProvider jwtTokenProvider;
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositorySupport userRepositorySupport;
    private final CompanyRepositorySupport companyRepositorySupport;
    private final MailServie mailServie;
    private final EmailConfirmJpaRepository emailConfirmJpaRepository;
    private final PassCodeJpaRepository passCodeJpaRepository;


    public TokenResponse register(UserRegisterCommand registerCommand) {
        User saved = save(registerCommand);
        TokenDTO dto = new TokenDTO(getToken(saved), saved);
        return new TokenResponse(dto);
    }

    public User save(UserRegisterCommand registerCommand) {

        return place(registerCommand);
    }

    public User place(UserRegisterCommand registerCommand) {
        checkAuthentication(registerCommand.getUserEmail(),registerCommand.getAuthentification());

        checkUserName(registerCommand.getUserName());
        checkUserEmail(registerCommand.getUserEmail());
        registerCommand.setEncodedPassword(passwordEncoder.encode(registerCommand.getPassword()));
        Company company=companyRepositorySupport.findById(registerCommand.getCompanyId());
//        List<Company> companyList =companyRepositorySupport.findAllById(registerCommand.getCompanyIds());
        User user = User.create(registerCommand,company);
//        user.addRole("ROLE_USER");

        return userJpaRepository.save(user);

    }

    private void checkAuthentication(String userEmail, String inputCode) {
        PassCode passCode=emailConfirmRepositorySupport.findCodeByEmailLast(userEmail);
        if (!passCode.getCode().equals(inputCode)){
            throw new AuthentificationMisMatchException(inputCode);
        }
    }

    private void checkUserEmail(String userEmail) {
        boolean exist=userJpaRepository.existsByUserEmail(userEmail);
        if(exist){
            throw new UserEmailDuplicatedException(userEmail);
        }
    }

    private String getToken(User user) {
        return jwtTokenProvider.createToken(user.getId().toString(), user.getRoles());
    }
    private void checkUserName(String userName) {
        userRepositorySupport.existByUsername(userName);
    }




    public boolean emailConfirm(String userEmail) {
        String code=mailServie.madeCode();
        PassCode passCode=PassCode.create(code);
        checkUserEmail(userEmail);


        EmailConfirm emailConfirm=existEmailConfirm(userEmail);
        passcodeDelete(userEmail);
        emailConfirm.addPassCode(passCode);

        MailDTO mailDto = new MailDTO();
        mailDto.setSubject("인증번호입니다.");
        mailDto.addText("인증코드입니다.\n\n아래의 인증코드를 입력해주세요\n");
        mailDto.addText("\n");
        mailDto.setCode(code);
        mailServie.mailSend(userEmail,mailDto);
        passCodeJpaRepository.save(passCode);
        emailConfirmJpaRepository.save(emailConfirm);
        return true;


    }

    private void passcodeDelete(String userEmail) {
//        passCodeJpaRepository.findAllByEmailConfirm();
        List<PassCode> passCodeList=emailConfirmRepositorySupport.findAllByEmailConfirm(userEmail);
            passCodeJpaRepository.deleteAll(passCodeList);


    }

    private EmailConfirm existEmailConfirm(String userEmail) {
        EmailConfirm emailConfirm=emailConfirmJpaRepository.findByEmail(userEmail);
        if (emailConfirm==null){
           return EmailConfirm.create(userEmail);
        }
        return emailConfirm;
    }

    public String passCode(String code, String userEmail) {
        PassCode passCode =emailConfirmRepositorySupport.findCodeByEmailLast(userEmail);

        if (!passCode.getCode().equals(code)){
            throw new PassCodeMisMatchException(code);
        }
        if (passCode.getCreateDate().isBefore(LocalDateTime.now().minusMinutes(15))){
            throw new PassCodeTimeOverException(LocalDateTime.now().toString());
        }
        return code;
    }
}
