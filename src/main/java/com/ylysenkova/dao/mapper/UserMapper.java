package com.ylysenkova.dao.mapper;

import com.ylysenkova.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dp-ptcstd-47 on 10/30/2017.
 */
public class UserMapper implements RowMapper<User>{


        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setUserName(resultSet.getString("user_name"));
            user.setUserEmail(resultSet.getString("user_email"));
            user.setUserNickname(resultSet.getString("nickname"));
            return user;
        }

}
