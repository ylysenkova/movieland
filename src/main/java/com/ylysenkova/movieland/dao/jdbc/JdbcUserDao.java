package com.ylysenkova.movieland.dao.jdbc;


import com.ylysenkova.movieland.dao.UserDao;
import com.ylysenkova.movieland.dao.mapper.UserMapper;
import com.ylysenkova.movieland.model.User;
import com.ylysenkova.movieland.web.exceptions.AuthentificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserDao implements UserDao{
    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserMapper userMapper = new UserMapper();

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private String getUserByEmail;

    @Override
    public User getUser(String email, String password) {
        logger.info("Method getUser is started.");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("email", email);
        sqlParameterSource.addValue("password", password);
        User user;
        try {
            user = namedParameterJdbcTemplate.queryForObject(getUserByEmail, sqlParameterSource, userMapper);
        } catch (RuntimeException e) {
            throw new AuthentificationException("Invalid username or password.");
        }
        logger.info("Method getUser returns = {} ", user);
        return user;
    }
}
