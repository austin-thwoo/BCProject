package com.laonstory.poc_be_spring.domain.user.application;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.company.dto.response.CompanySubCompanyResponse;
import com.laonstory.poc_be_spring.domain.company.persistance.CompanyRepositorySupport;
import com.laonstory.poc_be_spring.domain.company.persistance.SubCompanyRepositorySupport;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.response.UserResponse;
import com.laonstory.poc_be_spring.domain.user.persistance.UserRepositorySupport;
import com.laonstory.poc_be_spring.global.application.CustomDateService;
import com.laonstory.poc_be_spring.global.dto.DateDTO;
import com.laonstory.poc_be_spring.global.dto.request.PageRequest;
import com.laonstory.poc_be_spring.global.dto.response.ApiListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerQueryService {
    private final UserRepositorySupport userRepositorySupport;
    private final CustomDateService customDateService;
    private final SubCompanyRepositorySupport subCompanyRepositorySupport;
    private final CompanyRepositorySupport companyRepositorySupport;
    public ApiListResponse<UserResponse> findUserAll(String userType, String query, String startDate, String endDate,User user) {

        DateDTO dateDTO= new DateDTO();
        dateDTO=customDate(startDate,endDate,dateDTO);

        List<User> users=userRepositorySupport.findAll(userType,query,dateDTO,user.getId());

        return new ApiListResponse<>(users.stream().map(UserResponse::new).collect(Collectors.toList()));

    }

    private DateDTO customDate(String startDate, String endDate, DateDTO dateDTO) {
        return customDateService.dateConvert(startDate,endDate,dateDTO);
    }

    public UserResponse findUserById(UUID userId) {
        User user = userRepositorySupport.findById(userId);
        return new UserResponse(user);
    }

    public Page<CompanySubCompanyResponse> findAllSubCompany(User principal, int page, String query, Long companyId, CompanyType companyType) {

        PageRequest pageRequest =new PageRequest(page, 10);

        return subCompanyRepositorySupport.findAllPageBySubCompanyId(pageRequest.of(), query,companyId,companyType);

    }

    private Company getCompanyById(Long companyId) {
        return companyRepositorySupport.findById(companyId);
    }

    private User getUserById(UUID userId) {
        return userRepositorySupport.findById(userId);
    }
}
