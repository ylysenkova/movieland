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
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String getCountryByThreeMovieId;
    @Autowired
    private String getCountryByMovieId;
    @Autowired
    private String getAllCountries;
    @Autowired
    private String removeLinkCountryMovie;


    @Override
    public void enrichMoviesWithCountries(List<Movie> movieList) {
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

    @Override
    public void enrichMovieWithCountries(Movie movie) {
        logger.debug("Enrichment Movies with Countries is started.");
        int movieId = movie.getId();

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieId", movieId);

        List<Pair<Integer, Country>> countryMapList = namedParameterJdbcTemplate.query(getCountryByMovieId, sqlParameterSource,movieCountryMapper);
            List<Country> countries = new ArrayList<>();
            for (Pair<Integer, Country> movieCountryPair : countryMapList) {
                if (movie.getId() == movieCountryPair.getKey()) {
                    countries.add(movieCountryPair.getValue());
                }
                movie.setCountries(countries);
=======
        List<Pair<Integer, Country>> countryMapList = namedParameterJdbcTemplate.query(getCountryByMovieId, sqlParameterSource, movieCountryMapper);
        if(Thread.currentThread().isInterrupted()) {
            logger.info("Enrichment movie={} with Country was interrupted due to timeout", movie);
            return;
        }
        List<Country> countries = new ArrayList<>();
        for (Pair<Integer, Country> movieCountryPair : countryMapList) {
            if (movie.getId() == movieCountryPair.getKey()) {
                countries.add(movieCountryPair.getValue());
>>>>>>> Stashed changes
            }
    }

    @Override
    public List<Country> getAll() {
        logger.debug("Getting all countries from data base is starting");

        List<Country> countries = jdbcTemplate.query(getAllCountries, countryMapper);

        logger.info("There are countries are gotten from data base={} ", countries);
        return countries;
    }

    @Override
    public void removeCountryMovieLink(Movie movie) {
        logger.info("Removing link between Country and Movie is started.");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieId", movie.getId());
        namedParameterJdbcTemplate.update(removeLinkCountryMovie, sqlParameterSource);

        logger.info("Link between Country and Movie was removed.");
    }

}
