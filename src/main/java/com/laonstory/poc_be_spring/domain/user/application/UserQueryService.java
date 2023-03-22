package com.laonstory.poc_be_spring.domain.user.application;

import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanySubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.exception.DeletedSubCompanyException;
import com.laonstory.poc_be_spring.domain.company.exception.SubCompanyMisMatchException;
import com.laonstory.poc_be_spring.domain.company.persistance.SubCompanyRepositorySupport;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.request.TokenDTO;
import com.laonstory.poc_be_spring.domain.user.dto.response.SimpleUserResponse;
import com.laonstory.poc_be_spring.domain.user.dto.response.TokenResponse;
import com.laonstory.poc_be_spring.domain.user.persistance.UserRepositorySupport;
import com.laonstory.poc_be_spring.global.dto.request.PageRequest;
import com.laonstory.poc_be_spring.global.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class UserQueryService {
    private final UserRepositorySupport userRepositorySupport;
    private final TokenProvider tokenProvider;
    private final SubCompanyRepositorySupport subCompanyRepositorySupport;

    public TokenResponse findMe(User principal) {
        User user = getUserById(principal.getId());

        String token = tokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());
        TokenDTO dto = new TokenDTO(token, user);
        return new TokenResponse(dto);
//        return new UserResponse(getUserById(principal.getId()));


    }

    private User getUserById(UUID userId) {
        return userRepositorySupport.findById(userId);

    }

    public List<SimpleUserResponse> findUserByCompanyId(User principal, Long companyId) {
        List<User> users = userRepositorySupport.findByCompanyId(companyId);
        return users.stream().map(SimpleUserResponse::new).collect(Collectors.toList());
    }

    public CompanySubCompanyResponse findSubCompanyById(User principal, Long subCompanyId) {
        User user = getUserById(principal.getId());
        SubCompany subCompany = getSubCompanyById(subCompanyId);

        subCompanyUserCheck(user, subCompany);
        SubCompanyDeleteCheck(subCompany);
        return new CompanySubCompanyResponse(subCompany);
    }

    private void SubCompanyDeleteCheck(SubCompany subCompany) {
        if (subCompany.getDeletedDate() != null) {
            throw new DeletedSubCompanyException(subCompany.getId().toString());
        }
    }

    private void subCompanyUserCheck(User user, SubCompany subCompany) {
        if (!user.getCompany().getId().equals(subCompany.getCompany().getId()) && !adminCheck(user)) {
            throw new SubCompanyMisMatchException(subCompany.getId().toString());
        }
    }

    private SubCompany getSubCompanyById(Long subCompanyId) {
        return subCompanyRepositorySupport.findSubCompanyByCompanyId(subCompanyId);
    }


    private boolean adminCheck(User user) {
        return user.getRoles().stream().anyMatch(e -> e.equals("ROLE_ADMIN"));
    }


    private SubCompany getSubCompanyManage(Long subCompanyId) {
        return subCompanyRepositorySupport.findSubCompanyByCompanyId(subCompanyId);
    }

    public Page<CompanySubCompanyResponse> findAllSubCompany(User principal, int page, String query, CompanyType companyType) {
        PageRequest pageRequest = new PageRequest(page, 10);
        User user = getUserById(principal.getId());
        return subCompanyRepositorySupport.findAllPageBySubCompanyId(pageRequest.of(), query, user.getCompany().getId(), companyType);
    }
}
