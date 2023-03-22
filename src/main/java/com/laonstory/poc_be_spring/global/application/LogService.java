package com.laonstory.poc_be_spring.global.application;

import com.laonstory.poc_be_spring.domain.user.persistance.UserRepositorySupport;
import com.laonstory.poc_be_spring.global.domain.LogHistory;
import com.laonstory.poc_be_spring.global.persistance.LogHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class LogService {

    private final LogHistoryJpaRepository logHistoryJpaRepository;
    private final UserRepositorySupport userRepositorySupport;
    public void saveLog(HttpServletRequest httpServletRequest){
        String url = httpServletRequest.getRequestURI();

//        String ip = httpServletRequest.getHeader("X-Forwarded-For");
        String ip = getLocalServerIp(httpServletRequest);


        LogHistory logHistory=LogHistory.create(ip,url);

        logHistoryJpaRepository.save(logHistory);

    }
    private String getLocalServerIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        log.info("X-FORWARDED-FOR : " + ip);
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info("Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.info("WL-Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.info("HTTP_CLIENT_IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info("HTTP_X_FORWARDED_FOR : " + ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
            log.info("getRemoteAddr : "+ip);
        }
        log.info("Result : IP Address : "+ip);
        return ip;
    }
}

