package com.ylysenkova.movieland.service.impl;


import com.ylysenkova.movieland.dao.UserDao;
import com.ylysenkova.movieland.model.User;
import com.ylysenkova.movieland.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserWithRole(String mail, String password) {
        logger.info("Method getUserWithRole is started.");

        User userWithRoles = userDao.getUser(mail, password);
        userDao.enrichUserWithRoles(userWithRoles);

        return userWithRoles;
    }
}
