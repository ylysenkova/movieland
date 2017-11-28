package com.ylysenkova.movieland.service;


import com.ylysenkova.movieland.model.User;

public interface UserService {

    User getUserWithRole(String mail, String password);
}
