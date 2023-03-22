package com.laonstory.poc_be_spring.global.application;



import com.laonstory.poc_be_spring.domain.user.persistance.UserJpaRepository;
import com.laonstory.poc_be_spring.domain.user.domain.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService{


        private final UserJpaRepository userJpaRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = userJpaRepository.findById(UUID.fromString(userId)).orElse(null);


        return user;

    }
}
