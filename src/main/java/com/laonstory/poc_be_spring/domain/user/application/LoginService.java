package com.laonstory.poc_be_spring.domain.user.application;

import com.laonstory.poc_be_spring.domain.user.domain.PassCode;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.request.LoginCommand;
import com.laonstory.poc_be_spring.domain.user.dto.request.TokenDTO;
import com.laonstory.poc_be_spring.domain.user.dto.response.TokenResponse;
import com.laonstory.poc_be_spring.domain.user.exception.InvalidPasswordException;
import com.laonstory.poc_be_spring.domain.user.exception.PassCodeMisMatchException;
import com.laonstory.poc_be_spring.domain.user.exception.UnauthorisedException;
import com.laonstory.poc_be_spring.domain.user.persistance.EmailConfirmRepositorySupport;
import com.laonstory.poc_be_spring.domain.user.persistance.UserJpaRepository;
import com.laonstory.poc_be_spring.domain.user.persistance.UserRepositorySupport;
import com.laonstory.poc_be_spring.global.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService {
    private final UserJpaRepository userJpaRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final EmailConfirmRepositorySupport emailConfirmRepositorySupport;

    public TokenResponse login(LoginCommand loginCommand) {

        User user = getUserByUserName(loginCommand.getUserName());
        passwordCheck(loginCommand.getPassword(), user.getPassword());

        isExistRole(user.getRoles());
        checkCode(user.getUserEmail(), user.getAuthentification());
        String token = tokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());


        TokenDTO dto = new TokenDTO(token, user);
        return new TokenResponse(dto);
    }

    private void isExistRole(List<String> roles) {
        if (roles.isEmpty()) {
            throw new UnauthorisedException();
        }
    }

    private void checkCode(String userEmail, String authentification) {
        PassCode passCode = emailConfirmRepositorySupport.findCodeByEmailLast(userEmail);

        if (!authentification.equals(passCode.getCode())) {
            throw new PassCodeMisMatchException(userEmail);
        }
    }

    private void passwordCheck(String inputPassword, String userPassword) {
        boolean match = passwordEncoder.matches(inputPassword, userPassword);
        if (!match) {
            throw new InvalidPasswordException(inputPassword);
        }
    }

    private User getUserByUserName(String userName) {
        return userRepositorySupport.findByUsername(userName);
    }

}
