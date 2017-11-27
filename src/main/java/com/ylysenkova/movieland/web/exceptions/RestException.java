package com.ylysenkova.movieland.web.exceptions;


import org.springframework.http.HttpStatus;

public abstract class RestException extends RuntimeException{

    public RestException(String msg) { super(msg);
    }
    public abstract HttpStatus getHttpStatus();
}
