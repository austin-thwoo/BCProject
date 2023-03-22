package com.laonstory.poc_be_spring.global.config;

import com.google.common.net.HttpHeaders;
import com.laonstory.poc_be_spring.global.application.LogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class HttpInterCeptor extends HandlerInterceptorAdapter {

    private final LogService logService;

    public HttpInterCeptor(LogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("[REQ] ---> [METHOD] {} | [URL] {} | [qs] {} | [TOKEN] {} | reto {}",request.getMethod(), request.getRequestURI(), request.getQueryString(), request.getHeader(HttpHeaders.AUTHORIZATION),request.getHeader("Authorization: Bearer"));


        logService.saveLog(request);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[RES] ---> [STATUS] {}", response.getStatus());
        ServletOutputStream s  = response.getOutputStream();

        super.postHandle(request, response, handler, modelAndView);

    }




    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }



}
