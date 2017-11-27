package com.ylysenkova.movieland.dao.mapper;


import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRoleMapper implements RowMapper<Pair<Integer, Role>> {

    @Override
    public Pair<Integer, Role> mapRow(ResultSet resultSet, int i) throws SQLException {

        Pair<Integer, Role> roleByUserId;

        int userId = resultSet.getInt("user_id");
        int id = resultSet.getInt("id");
        String role = resultSet.getString("role");
        roleByUserId = new Pair<>(userId, Role.getRole(role));

        return roleByUserId;
    }
}
