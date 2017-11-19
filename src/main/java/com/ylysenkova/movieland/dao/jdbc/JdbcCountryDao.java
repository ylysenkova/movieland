package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.CountryDao;
import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.dao.mapper.MovieCountryMapper;
import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository(value = "jdbcCountryDao")
public class JdbcCountryDao implements CountryDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MovieCountryMapper movieCountryMapper = new MovieCountryMapper();

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private String getCountryByThreeMovieId;

    @Override
    public void enrichMovieWithCountries(List<Movie> movieList) {
        logger.debug("Enrichment Movies with Countries is started.");
        Set<Integer> movieIds = new HashSet<>();

        for (Movie movie : movieList) {
            movieIds.add(movie.getId());
        }

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieIds", movieIds);
        List<Pair<Integer, Country>> countryMapList = namedParameterJdbcTemplate.query(getCountryByThreeMovieId, sqlParameterSource,movieCountryMapper);
        for (Movie movies : movieList) {
            List<Country> countries = new ArrayList<>();
            for (Pair<Integer, Country> movieCountryPair : countryMapList) {
                if (movies.getId() == movieCountryPair.getKey()) {
                    countries.add(movieCountryPair.getValue());
                }
            movies.setCountries(countries);
            }
        }

    }

}
