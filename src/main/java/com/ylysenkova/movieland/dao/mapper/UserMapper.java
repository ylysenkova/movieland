package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User>{


        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("user_name"));
            user.setEmail(resultSet.getString("user_email"));
            user.setNickname(resultSet.getString("nickname"));
            return user;
        }

}
