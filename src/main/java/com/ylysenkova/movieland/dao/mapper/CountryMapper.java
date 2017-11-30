package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Country;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country>{

    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Country country = new Country(id, name);
        return country;
    }
}
