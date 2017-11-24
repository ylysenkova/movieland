package com.ylysenkova.movieland.model;


import java.time.LocalDateTime;

public class Token {
    private final User user;
    private final LocalDateTime expiredTime;


    public Token(User user, LocalDateTime expiredTime) {
        this.user = user;
        this.expiredTime = expiredTime;
    }

    public User getUser() {
        return user;
    }
    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }
}
