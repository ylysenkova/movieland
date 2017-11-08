package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Genre;
import javafx.util.Pair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MovieGenreMapper implements RowMapper<Pair<Integer, Genre>> {

    @Override
    public Pair<Integer, Genre> mapRow(ResultSet resultSet, int i) throws SQLException {
        Genre genre = new Genre();
        Pair<Integer, Genre> genreByMovieId;

        genre.setId(resultSet.getInt("id"));
        int movieId = resultSet.getInt("movie_id");
        genre.setName(resultSet.getString("name"));
        genreByMovieId = new Pair<>(movieId, genre);

        return genreByMovieId;
    }
}

