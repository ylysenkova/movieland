package com.ylysenkova.movieland.dao;

import com.ylysenkova.movieland.model.User;

public interface UserDao {

    User getUser(String email, String password);

    void enrichUserWithRoles (User user);
}
