package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setNameRussian(resultSet.getString("name_Russian"));
        movie.setNameNative(resultSet.getString("name_Native"));
        movie.setYearOfRelease(resultSet.getInt("year_Of_Release"));
        movie.setDescription(resultSet.getString("description"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setPrice(resultSet.getDouble("price"));
        movie.setPicturePath(resultSet.getString("picture_Path"));
        return movie;
    }

}
