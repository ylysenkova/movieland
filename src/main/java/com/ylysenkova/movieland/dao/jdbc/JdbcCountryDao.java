package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.CountryDao;
import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.dao.mapper.CountryMapper;
import com.ylysenkova.movieland.dao.mapper.MovieCountryMapper;
import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository(value = "jdbcCountryDao")
public class JdbcCountryDao implements CountryDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MovieCountryMapper movieCountryMapper = new MovieCountryMapper();
    private final CountryMapper countryMapper = new CountryMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private String getCountryByThreeMovieIdSQL;
    @Autowired
    private String getCountryByMovieIdSQL;
    @Autowired
    private String getAllCountriesSQL;
    @Autowired
    private String removeLinkCountryMovieSQL;
    @Autowired
    private String insertMovieCountrySQL;


    @Override
    public void enrichMoviesWithCountries(List<Movie> movieList) {
        logger.debug("Enrichment Movies with Countries is started.");
        Set<Integer> movieIds = new HashSet<>();

        for (Movie movie : movieList) {
            movieIds.add(movie.getId());
        }

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieIds", movieIds);
        List<Pair<Integer, Country>> countryMapList = namedParameterJdbcTemplate.query(getCountryByThreeMovieIdSQL, sqlParameterSource,movieCountryMapper);
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

    @Override
    public void enrichMovieWithCountries(Movie movie) {
        logger.debug("Enrichment Movies with Countries is started.");
        int movieId = movie.getId();

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieId", movieId);

        List<Pair<Integer, Country>> countryMapList = namedParameterJdbcTemplate.query(getCountryByMovieIdSQL, sqlParameterSource, movieCountryMapper);
        if(Thread.currentThread().isInterrupted()) {
            logger.info("Enrichment movie={} with Country was interrupted due to timeout", movie);
            return;
        }
        List<Country> countries = new ArrayList<>();
        for (Pair<Integer, Country> movieCountryPair : countryMapList) {
            if (movie.getId() == movieCountryPair.getKey()) {
                countries.add(movieCountryPair.getValue());
            }
        }
        movie.setCountries(countries);
    }

    @Override
    public List<Country> getAll() {
        logger.debug("Getting all countries from data base is starting");

        List<Country> countries = jdbcTemplate.query(getAllCountriesSQL, countryMapper);

        logger.info("There are countries are gotten from data base={} ", countries);
        return countries;
    }

    @Override
    public void removeCountryMovieLink(Movie movie) {
        logger.info("Removing link between Country and Movie is started.");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieId", movie.getId());
        namedParameterJdbcTemplate.update(removeLinkCountryMovieSQL, sqlParameterSource);

        logger.info("Link between Country and Movie was removed.");
    }

    @Override
    public void editAddCountry(Movie movie) {
        removeCountryMovieLink(movie);
        List<Map<String, Integer>> sqlParametersMap = new ArrayList<>();
        Map<String, Integer> fillSqlParameters = new HashMap<>();
        for (Country country : movie.getCountries()) {
            fillSqlParameters.put("movieId", movie.getId());
            sqlParametersMap.add(fillSqlParameters);
            fillSqlParameters.put("countryId", country.getId());
            sqlParametersMap.add(fillSqlParameters);
        }
        SqlParameterSource[] sqlParameters = SqlParameterSourceUtils.createBatch(sqlParametersMap.toArray());
        namedParameterJdbcTemplate.batchUpdate(insertMovieCountrySQL, sqlParameters);
    }

}
