package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Country;
import javafx.util.Pair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class MovieCountryMapper implements RowMapper<HashMap<Integer, Country>> {
    @Override
    public HashMap<Integer, Country> mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();
        HashMap<Integer, Country> countryByMovieId;

        country.setId(resultSet.getInt("id"));
        int movieId = resultSet.getInt("movie_id");
        country.setName(resultSet.getString("name"));
        countryByMovieId = new HashMap<>();
        countryByMovieId.put(movieId, country);

        return countryByMovieId;
    }
}
