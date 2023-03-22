package com.laonstory.poc_be_spring.domain.user.application;

import com.laonstory.poc_be_spring.domain.company.dto.response.CompanyResponse;
import com.laonstory.poc_be_spring.domain.company.persistance.CompanyRepositorySupport;
import com.laonstory.poc_be_spring.domain.user.domain.EmailConfirm;
import com.laonstory.poc_be_spring.domain.user.domain.PassCode;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.request.FindPasswordCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.MailDTO;
import com.laonstory.poc_be_spring.domain.user.dto.response.SimpleUserResponse;
import com.laonstory.poc_be_spring.domain.user.exception.InfoMisMatchException;
import com.laonstory.poc_be_spring.domain.user.exception.PassCodeMisMatchException;
import com.laonstory.poc_be_spring.domain.user.exception.UserNameDuplicatedException;
import com.laonstory.poc_be_spring.domain.user.persistance.*;
import com.laonstory.poc_be_spring.global.dto.request.PageRequest;
import com.laonstory.poc_be_spring.global.dto.response.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {


    private final CompanyRepositorySupport companyRepositorySupport;
    private final UserJpaRepository userJpaRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final MailServie mailServie;
    private final EmailConfirmRepositorySupport emailConfirmRepositorySupport;
    private final EmailConfirmJpaRepository emailConfirmJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final PassCodeJpaRepository passCodeJpaRepository;


    public PagingResponse<CompanyResponse> findCompanyAll(int page, String query) {
        PageRequest pageRequest = new PageRequest(page, 10);
        Page<CompanyResponse> companyPage = companyRepositorySupport.findAll(pageRequest.of(), query);
        return new PagingResponse<>(companyPage);

    }

    public boolean usernameOverLap(String userName) {
        boolean exist = userJpaRepository.existsByUserName(userName);

        if (exist) {
            throw new UserNameDuplicatedException(userName);
        }
        return true;
    }

    public SimpleUserResponse sendCode(FindPasswordCommand findPasswordCommand) {
        User user = checkUser(findPasswordCommand);
        String code = mailServie.madeCode();
        passcodeDelete(user.getUserEmail());
        PassCode passCode = PassCode.create(code);
        EmailConfirm emailConfirm = existEmailConfirm(user.getUserEmail());
        emailConfirm.addPassCode(passCode);
        user.updateAuthentication(code);
        MailDTO mailDTO = sendCodeSetMailDTO(code);
        mailServie.mailSend(user.getUserEmail(), mailDTO);

//        passwordEncoder.encode()


        return new SimpleUserResponse(user);
    }

    private void passcodeDelete(String userEmail) {
        List<PassCode> passCodeList=emailConfirmRepositorySupport.findCodeAllByEmail(userEmail);
        passCodeJpaRepository.deleteAll(passCodeList);

    }

    private MailDTO sendCodeSetMailDTO(String code) {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setSubject("비밀번호 찾기 인증코드 입니다.");
        mailDTO.addText("귀하의 인증 코드입니다\n\n");
        mailDTO.addText("아래의 인증코드를 입력해 주세요.\n\n");
        mailDTO.setCode(code);
        return mailDTO;
    }

    private EmailConfirm existEmailConfirm(String userEmail) {
        EmailConfirm emailConfirm = emailConfirmJpaRepository.findByEmail(userEmail);
        if (emailConfirm == null) {
            return emailConfirmJpaRepository.save(EmailConfirm.create(userEmail));
        }
        return emailConfirm;
    }

    private User checkUser(FindPasswordCommand findPasswordCommand) {
        User user = userRepositorySupport.findByUsername(findPasswordCommand.getUserName());
        if (!user.getName().equals(findPasswordCommand.getName())) {
            throw new InfoMisMatchException(findPasswordCommand.getName());
        }
        if (!user.getUserPhone().equals(findPasswordCommand.getUserPhone())) {
            throw new InfoMisMatchException(findPasswordCommand.getUserPhone());
        }
        return user;
    }

    public boolean sendPassword(FindPasswordCommand findPasswordCommand) {

        User user = findUser(findPasswordCommand);
        codeCheck(findPasswordCommand, user);

        String temporaryPassword = mailServie.madeCode();

        //비번변경
        user.changePassword(passwordEncoder.encode(temporaryPassword));
        //인증변경
        String foundCode=emailConfirmRepositorySupport.findCodeByEmailLast(user.getUserEmail()).getCode();
        user.updateAuthentication(foundCode);
        //메일센드
        MailDTO mailDTO = sendPasswordSetMailDto(temporaryPassword);
//        user.updateAuthentication(code);
        mailServie.mailSend(user.getUserEmail(), mailDTO);

        return true;
    }

    private MailDTO sendPasswordSetMailDto(String code) {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setSubject("임시 비밀번호입니다.");
        mailDTO.setCode(code);
        mailDTO.addText("귀하의 임시 비밀번호입니다. ");
        mailDTO.addText("아래의 비밀번호로 로그인 해주시기 바랍니다.\n\n 로그인후 비밀번호를 변경해 주세요.\n\n");
        return mailDTO;
    }

    private void codeCheck(FindPasswordCommand findPasswordCommand, User user) {

        PassCode passCode = emailConfirmRepositorySupport.findCodeByEmailLast(user.getUserEmail());
        if (!passCode.getCode().equals(findPasswordCommand.getCode())) {
            throw new PassCodeMisMatchException(findPasswordCommand.getCode());
        }
    }

    private User findUser(FindPasswordCommand findPasswordCommand) {
        User user = userRepositorySupport.findByUsername(findPasswordCommand.getUserName());
        return user;

    }
}
