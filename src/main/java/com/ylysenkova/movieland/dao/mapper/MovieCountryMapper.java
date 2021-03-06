package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MovieCountryMapper implements RowMapper<Pair<Integer, Country>> {
    @Override
    public Pair<Integer, Country> mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();
        Pair<Integer, Country> countryByMovieId;

        country.setId(resultSet.getInt("id"));
        int movieId = resultSet.getInt("movie_id");
        country.setName(resultSet.getString("name"));
        countryByMovieId = new Pair<>(movieId, country);

        return countryByMovieId;
    }
}
