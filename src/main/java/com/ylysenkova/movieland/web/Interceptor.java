package com.ylysenkova.movieland.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ylysenkova.movieland.model.User;
import com.ylysenkova.movieland.security.Protected;
import com.ylysenkova.movieland.security.SecurityHttpServletRequestWrapper;
import com.ylysenkova.movieland.security.UserPrincipal;
import com.ylysenkova.movieland.service.AuthenticationService;
import com.ylysenkova.movieland.web.dto.response.ExceptionResponse;
import com.ylysenkova.movieland.web.exception.AuthenticationException;
import com.ylysenkova.movieland.web.exception.PermissionException;
import com.ylysenkova.movieland.web.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class Interceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Pre handle");
        String header = request.getHeader("x-auth-token");
        User user;
        boolean isGuest;

        if (header != null && authenticationService.isAlive(UUID.fromString(header))) {
            isGuest = false;
            user = authenticationService.getUserByUuid(UUID.fromString(header));
            UUID requestId = UUID.randomUUID();
            MDC.put("requestId", String.valueOf(requestId));
            MDC.put("login", authenticationService.getUserMailByUuid(UUID.fromString(header)));
        } else {
            isGuest = true;
            user = new User();
            user.setName("guest");
            UUID requestId = UUID.randomUUID();
            MDC.put("requestId", String.valueOf(requestId));
            MDC.put("login", user.getName());

        }
        logger.debug("Getting token.");
        try {
            validateUserRole(user, handler, isGuest);
            UserPrincipal principal = new UserPrincipal(user);
            logger.debug("Principal {} is received", principal);
            ((SecurityHttpServletRequestWrapper) request).setPrincipal(principal);
        } catch (RestException e) {
            response.setStatus(e.getHttpStatus().value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new ExceptionResponse(e.getMessage())));
            return false;
        }

        return true;
    }

    public void validateUserRole(User user, Object handler, boolean isGuest) {
        logger.debug("Token is validated.");
        logger.info("Validating token for user {} " + user + " handler {} " + handler + " is Guest {} " + isGuest);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.hasMethodAnnotation(Protected.class)) {
            if (isGuest) {
                throw new AuthenticationException("Only logged in users can add review.");
            } else {
                Protected annotation = handlerMethod.getMethodAnnotation(Protected.class);
                if (!user.getRole().contains(annotation.value())) {
                    throw new PermissionException("Permission denied.");
                }
            }
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        MDC.clear();
    }

}