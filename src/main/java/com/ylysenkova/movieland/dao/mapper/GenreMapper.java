package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {


    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Genre genre = new Genre(id, name);
        return genre;
    }

}
