package com.laonstory.poc_be_spring.domain.user.application;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.company.dto.request.SubCompanyCommand;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanyResponse;
import com.laonstory.poc_be_spring.domain.company.dto.response.SubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.exception.CompanySerialCodeDuplicatedException;

import com.laonstory.poc_be_spring.domain.company.exception.DeletedSubCompanyException;
import com.laonstory.poc_be_spring.domain.company.exception.SubCompanyMisMatchException;
import com.laonstory.poc_be_spring.domain.company.persistance.*;
import com.laonstory.poc_be_spring.domain.examination.dto.request.UserDidCommand;
import com.laonstory.poc_be_spring.domain.user.domain.PassCode;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.request.ChangePasswordCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.CompanyRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.UserRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.dto.response.UserResponse;
import com.laonstory.poc_be_spring.domain.user.exception.*;
import com.laonstory.poc_be_spring.domain.user.persistance.EmailConfirmRepositorySupport;
import com.laonstory.poc_be_spring.domain.user.persistance.UserRepositorySupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserCommandService {


    private final UserRepositorySupport userRepositorySupport;
    private final PasswordEncoder passwordEncoder;
    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyRepositorySupport companyRepositorySupport;
    private final UserUtilsService userUtilsService;
    private final EmailConfirmRepositorySupport emailConfirmRepositorySupport;
    private final SubCompanyRepositorySupport subCompanyRepositorySupport;
    private final SubCompanyJpaRepository subCompanyJpaRepository;


    public UserCommandService(UserRepositorySupport userRepositorySupport,
                              PasswordEncoder passwordEncoder,
                              CompanyJpaRepository companyJpaRepository,
                              CompanyRepositorySupport companyRepositorySupport,
                              UserUtilsService userUtilsService,
                              EmailConfirmRepositorySupport emailConfirmRepositorySupport, SubCompanyRepositorySupport subCompanyRepositorySupport, SubCompanyJpaRepository subCompanyJpaRepository) {

        this.userRepositorySupport = userRepositorySupport;
        this.passwordEncoder = passwordEncoder;
        this.companyJpaRepository = companyJpaRepository;
        this.companyRepositorySupport = companyRepositorySupport;
        this.userUtilsService = userUtilsService;
        this.emailConfirmRepositorySupport = emailConfirmRepositorySupport;
        this.subCompanyRepositorySupport = subCompanyRepositorySupport;
        this.subCompanyJpaRepository = subCompanyJpaRepository;

    }


    public CompanyResponse save(CompanyRegisterCommand companyRegisterCommand) {
        serialCodeCheck(companyRegisterCommand.getSerialCode());
        Company company = companyJpaRepository.save(Company.place(companyRegisterCommand));

        return new CompanyResponse(company);

    }

    private void serialCodeCheck(String serialCode) {
        Optional<Company> company = companyJpaRepository.findBySerialCode(serialCode);
        if (company.isPresent()) {
            throw new CompanySerialCodeDuplicatedException(serialCode);
        }
    }


    public UserResponse updateMe(User principal, UserRegisterCommand command) {
        User user = getUserById(principal.getId());
        emailUpdate(user, command);
        Company company = getCompanyById(command.getCompanyId());
//        checkCode(user.getUserEmail(), command.getAuthentification());
        user.update(command, company);
        return new UserResponse(user);

    }

    private void emailUpdate(User user, UserRegisterCommand command) {
        if (!user.getUserEmail().equals(command.getUserEmail())) {
            userRepositorySupport.checkUserEmail(command.getUserEmail());//쓰는이메일인지 확인
            checkAuthentication(command.getUserEmail(), command.getAuthentification());
            user.modifyEmail(command.getUserEmail());
        }
    }

    private void checkAuthentication(String userEmail, String inputCode) {
        PassCode passCode = emailConfirmRepositorySupport.findCodeByEmailLast(userEmail);
        if (!passCode.getCode().equals(inputCode)) {
            throw new AuthentificationMisMatchException(inputCode);
        }
    }

    private void checkCode(String userEmail, String authentication) {
        PassCode passCode = emailConfirmRepositorySupport.findCodeByEmailLast(userEmail);
        if (!passCode.getCode().equals(authentication)) {
            throw new PassCodeMisMatchException(userEmail);
        }
    }

    private Company getCompanyById(Long companyId) {
        return companyRepositorySupport.findById(companyId);
    }

    private User getUserById(UUID userId) {
        return userRepositorySupport.findById(userId);
    }

    public boolean changePassword(User principal, ChangePasswordCommand changePasswordCommand) {
        User user = userRepositorySupport.findById(principal.getId());

        //현재 비번이랑 같은지
        duplicationCheckPassword(changePasswordCommand.getNewPassword(), user.getPassword());
        // 현재 비밀번호 잘 입력 했는지
        this.passwordCheck(changePasswordCommand.getCurrentPassword(), user.getPassword());

        // newPassword와 checkNewPassword가 같은 지
        userUtilsService.checkEqualsNewPassword(changePasswordCommand.getNewPassword(), changePasswordCommand.getCheckNewPassword());

        // update
        user.changePassword(passwordEncoder.encode(changePasswordCommand.getNewPassword()));
        return true;
    }

    public void duplicationCheckPassword(String newPassword, String encodedPassword) {
        if (passwordEncoder.matches(newPassword, encodedPassword)) {
            throw new DuplicationPasswordException(newPassword);
        }
    }

    private void passwordCheck(String inputPassword, String userPassword) {
        boolean match = passwordEncoder.matches(inputPassword, userPassword);
        if (!match) {
            throw new InvalidPasswordException(inputPassword);
        }
    }

    public boolean updateUserDid(UserDidCommand didCommand) {
        User user = getUser(didCommand.getUserId());
        user.addDid(didCommand.getDidAddress());
        return true;

    }

    private User getUser(UUID applicantId) {
        return userRepositorySupport.findById(applicantId);
    }

    public SubCompanyResponse saveSubCompany(User principal, SubCompanyCommand subCompanyCommand) {
        User user = getUser(principal.getId());
        return placeSubCompany(user.getCompany(), subCompanyCommand);
    }


    public SubCompanyResponse placeSubCompany(Company company, SubCompanyCommand subCompanyCommand) {
        duplicationCheckSubCompany(subCompanyCommand, company.getId());
        SubCompany subCompany = SubCompany.place(subCompanyCommand, company);
        subCompanyJpaRepository.save(subCompany);


        return new SubCompanyResponse(subCompany);


    }

    private void duplicationCheckSubCompany(SubCompanyCommand subCompanyCommand, Long companyId) {
        subCompanyRepositorySupport.duplicationCheck(subCompanyCommand, companyId);
    }

    private void subSerialCodeCheck(String serialCode) {
        Optional<SubCompany> subCompany = subCompanyJpaRepository.findBySerialCode(serialCode);
        if (subCompany.isPresent()) {
            throw new CompanySerialCodeDuplicatedException(serialCode);
        }
    }

    public SubCompanyResponse modifySubCompany(User principal, SubCompanyCommand subCompanyCommand) {
        User user = getUserById(principal.getId());
        SubCompany subCompany = getSubCompanyById(subCompanyCommand.getSubCompanyId());
        subCompanyUserCheck(user, subCompany);
        subCompanyDeleteCheck(subCompany);
        subCompany.modify(subCompanyCommand);

        return new SubCompanyResponse(subCompany);
    }

    private void subCompanyDeleteCheck(SubCompany subCompany) {
        if (subCompany.getDeletedDate() != null) {
            throw new DeletedSubCompanyException(subCompany.getId().toString());
        }
    }

    private void subCompanyUserCheck(User user, SubCompany subCompany) {
        if (!user.getCompany().getId().equals(subCompany.getCompany().getId()) && !adminCheck(user)) {
            throw new SubCompanyMisMatchException(subCompany.getId().toString());
        }
    }


    private SubCompany getSubCompanyById(Long SubCompanyId) {
        return subCompanyRepositorySupport.findSubCompanyByCompanyId(SubCompanyId);
    }

    private boolean adminCheck(User user) {
        return user.getRoles().stream().anyMatch(e -> e.equals("ROLE_ADMIN"));
    }

    public SubCompanyResponse deleteSubCompanyBySubCompanyId(User principal, Long subCompanyId) {
        User user = getUserById(principal.getId());
        SubCompany subCompany = getSubCompanyById(subCompanyId);
        subCompanyUserCheck(user, subCompany);
        subCompanyDeleteCheck(subCompany);
        subCompany.setDeletedDate();
        return new SubCompanyResponse(subCompany);

    }

    public SubCompanyResponse unDeleteSubCompanyBySubCompanyId(User principal, Long subCompanyId) {
        User user = getUserById(principal.getId());
        SubCompany subCompany = getSubCompanyById(subCompanyId);
        subCompanyUserCheck(user, subCompany);
        subCompany.setUnDeletedDate();
        return new SubCompanyResponse(subCompany);
    }
}
