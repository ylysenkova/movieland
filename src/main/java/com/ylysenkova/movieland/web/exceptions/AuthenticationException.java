package com.ylysenkova.movieland.web.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends RestException {

    public AuthenticationException(String exceptionMessage) {
        super(exceptionMessage);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
