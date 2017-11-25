package com.ylysenkova.movieland.web.response;

public class TextMessageResponse {
    private String message;

    public TextMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
