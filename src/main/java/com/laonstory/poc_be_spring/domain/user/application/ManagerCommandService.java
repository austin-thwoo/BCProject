package com.laonstory.poc_be_spring.domain.user.application;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.company.dto.request.SubCompanyCommand;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanyResponse;
import com.laonstory.poc_be_spring.domain.company.dto.response.SubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.persistance.CompanyRepositorySupport;

import com.laonstory.poc_be_spring.domain.company.persistance.SubCompanyJpaRepository;
import com.laonstory.poc_be_spring.domain.company.persistance.SubCompanyRepositorySupport;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.request.CompanyRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.UserRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.dto.response.UserResponse;
import com.laonstory.poc_be_spring.domain.user.persistance.UserRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerCommandService {

    private final CompanyRepositorySupport companyRepositorySupport;
    private final UserRepositorySupport userRepositorySupport;
    private final SubCompanyRepositorySupport subCompanyRepositorySupport;
    private final SubCompanyJpaRepository subCompanyJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyResponse update(CompanyRegisterCommand registerCommand) {
        Company company = getCompanyById(registerCommand.getCompanyId());
        company.updateInfo(registerCommand);

        return new CompanyResponse(company);
    }

    public CompanyResponse findCompanyById(Long companyId) {
        return new CompanyResponse(getCompanyById(companyId));

    }

    public Company getCompanyById(Long companyId) {
        return companyRepositorySupport.findById(companyId);
    }

    public CompanyResponse deleteCompanyById(Long companyId) {
        Company company = getCompanyById(companyId);
        company.setDeletedDate();
        return new CompanyResponse(company);
    }

    public UserResponse updateUser(UUID userId, UserRegisterCommand command) {
        User user = getUserById(userId);
        Company company = companyRepositorySupport.findById(command.getCompanyId());
        if (!command.getPassword().isEmpty()) {
            command.setEncodedPassword(passwordEncoder.encode(command.getPassword()));
            user.update2(command,company);
            return new UserResponse(user);
        }
        user.update(command, company);

        return new UserResponse(user);
    }

    public boolean deleteUser(UUID userId) {
        User user = getUserById(userId);
        user.setDeletedDate();
        return true;
    }

    public boolean unDeleteUser(UUID userId) {
        User user = getUserById(userId);
        user.setUnDeletedDate();
        return true;
    }

    private User getUserById(UUID userId) {
        return userRepositorySupport.findUserById(userId);
    }

    public boolean unDeleteCompanyById(Long companyId) {
        Company company = getCompanyById(companyId);
        company.setUnDeletedDate();
        return true;
    }


    public boolean approveUser(User principal, UUID userId) {
        User user = getUserById(userId);
        user.addRole("ROLE_USER");
        return true;
    }




    private List<SubCompany> getSubCompanyList(List<Long> subCompanyId) {
        return subCompanyRepositorySupport.findAllBySubCompanyId(subCompanyId);
    }
    public SubCompanyResponse saveSubCompany(User principal, SubCompanyCommand subCompanyCommand){

        Company company=companyRepositorySupport.findById(subCompanyCommand.getCompanyId());
        return placeSubCompany(company,subCompanyCommand);
    }
    public SubCompanyResponse placeSubCompany(Company company, SubCompanyCommand subCompanyCommand) {

        SubCompany subCompany = SubCompany.place(subCompanyCommand,company);
        subCompanyJpaRepository.save(subCompany);


        return new SubCompanyResponse(subCompany);


    }
}
