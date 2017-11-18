package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Pair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class MovieGenreMapper implements RowMapper<Pair<Integer, Genre>> {

    @Override
    public Pair<Integer, Genre> mapRow(ResultSet resultSet, int i) throws SQLException {

        Pair<Integer, Genre> genreByMovieId;

        int id = resultSet.getInt("id");
        int movieId = resultSet.getInt("movie_id");
        String name = (resultSet.getString("name"));
        Genre genre = new Genre(id, name);
        genreByMovieId = new Pair<>(movieId, genre);

        return genreByMovieId;
    }
}

