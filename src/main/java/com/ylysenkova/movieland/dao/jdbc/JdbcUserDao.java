package com.ylysenkova.movieland.dao.jdbc;


import com.ylysenkova.movieland.dao.UserDao;
import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.dao.mapper.UserMapper;
import com.ylysenkova.movieland.dao.mapper.UserRoleMapper;
import com.ylysenkova.movieland.model.Role;
import com.ylysenkova.movieland.model.User;
import com.ylysenkova.movieland.web.exception.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcUserDao implements UserDao{
    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserMapper userMapper = new UserMapper();
    private UserRoleMapper userRoleMapper = new UserRoleMapper();

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private String getUserByEmailSQL;
    @Autowired
    private String getRoleByUserIdSQL;

    @Override
    public User getUser(String email, String password) {
        logger.info("Method getUser is started.");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("email", email);
        sqlParameterSource.addValue("password", password);
        User user;
        try {
            user = namedParameterJdbcTemplate.queryForObject(getUserByEmailSQL, sqlParameterSource, userMapper);
        } catch (RuntimeException e) {
            throw new AuthenticationException("Invalid username or password.");
        }
        logger.info("Method getUser returns = {} ", user);
        return user;
    }

    public void enrichUserWithRoles (User user) {
        logger.info("Enrichment Users with Roles are started...");

        int userId = user.getId();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("userId", userId);

        List<Pair<Integer, Role>> roleList = namedParameterJdbcTemplate.query(getRoleByUserIdSQL, sqlParameterSource, userRoleMapper);


            List<Role> roles = new ArrayList<>();

            for (Pair<Integer, Role> rolePair : roleList) {
                if(user.getId() == rolePair.getKey()) {
                    roles.add(rolePair.getValue());
                }
            }
            user.setRoles(roles);

    }
}
