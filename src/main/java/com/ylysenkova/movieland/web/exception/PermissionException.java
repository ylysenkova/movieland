package com.ylysenkova.movieland.web.exception;


import org.springframework.http.HttpStatus;

public class PermissionException extends RestException {

    public PermissionException(String exceptionMessage) {
        super((exceptionMessage));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
