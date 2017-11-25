package com.ylysenkova.movieland.web.controller;


import com.ylysenkova.movieland.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class Interceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Pre handle");
        String header = request.getHeader("x-auth-token");

        if (header != null && authenticationService.isAlive(UUID.fromString(header))) {
            UUID requestId = UUID.randomUUID();
            MDC.put("requestId", String.valueOf(requestId));
            MDC.put("login", authenticationService.getUserMailByUuid(UUID.fromString(header)));
        }
        else {
            UUID requestId = UUID.randomUUID();
            MDC.put("requestId", String.valueOf(requestId));
            MDC.put("login", "guest");

        }
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        logger.info("Post handle");
//        MDC.clear();
//
//    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        MDC.clear();
    }

}