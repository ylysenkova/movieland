package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Genre;
import javafx.util.Pair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class MovieGenreMapper implements RowMapper<HashMap<Integer, Genre>> {

    @Override
    public HashMap<Integer, Genre> mapRow(ResultSet resultSet, int i) throws SQLException {

        HashMap<Integer, Genre> genreByMovieId = new HashMap<>();

        int id = resultSet.getInt("id");
        int movieId = resultSet.getInt("movie_id");
        String name = (resultSet.getString("name"));
        Genre genre = new Genre(id, name);
        genreByMovieId.put(movieId, genre);

        return genreByMovieId;
    }
}

