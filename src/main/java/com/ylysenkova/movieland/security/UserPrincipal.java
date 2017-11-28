package com.ylysenkova.movieland.security;


import com.ylysenkova.movieland.model.Token;
import com.ylysenkova.movieland.model.User;

import java.security.Principal;
import java.util.Optional;

public class UserPrincipal implements Principal{
    private User user;

    public UserPrincipal(User user) {
        this.user = user;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return "guest";
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "user=" + user +
                '}';
    }
}
