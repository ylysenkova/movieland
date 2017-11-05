package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Country;
import javafx.util.Pair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MovieCountryMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();
        Pair<Integer, Country> countryByMovieId;

        country.setId(resultSet.getInt("id"));
        int movieId = resultSet.getInt("movie_id");
        country.setName(resultSet.getString("name"));
        countryByMovieId = new Pair<>(movieId, country);

        return countryByMovieId;
    }
}
