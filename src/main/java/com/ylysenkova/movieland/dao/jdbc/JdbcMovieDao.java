package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.MovieDao;
import com.ylysenkova.movieland.dao.jdbc.utils.QueryBuilder;
import com.ylysenkova.movieland.dao.mapper.MovieCountryMapper;
import com.ylysenkova.movieland.dao.mapper.MovieGenreMapper;
import com.ylysenkova.movieland.dao.mapper.MovieMapper;
import com.ylysenkova.movieland.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.*;


@Repository
public class JdbcMovieDao implements MovieDao {

    private final MovieMapper movieMapper = new MovieMapper();
    private final MovieCountryMapper movieCountryMapper = new MovieCountryMapper();
    private final MovieGenreMapper movieGenreMapper = new MovieGenreMapper();
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Random random = new Random();

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private String getAllMovies;
    @Autowired
    private String getThreeMovies;
    @Autowired
    private String getMovieCount;
    @Autowired
    private String getCountryByThreeMovieId;
    @Autowired
    private String getGenreByThreeMovieId;
    @Autowired
    private String getMovieByGenreId;

    @Override
    public List<Movie> getAll() {
        logger.debug("Method getAll has started");
        List<Movie> moviesList = jdbcTemplate.query(getAllMovies, movieMapper);
        logger.debug("Method getAll returned = {}", moviesList);
        return moviesList;
    }

    @Override
    public Set<Integer> getThreeMovieIds() {
        logger.debug("Method getThreeMovieIds has started");
        Set<Integer> movieIds = new HashSet<>();

        int movieCount = jdbcTemplate.queryForObject(getMovieCount, Integer.class);
        int serchCount = 0;

        if (movieCount < 3) {
            serchCount = movieCount;
        } else {
            serchCount = 3;
        }
        while (movieIds.size() < serchCount) {
            movieIds.add(random.nextInt(movieCount) + 1);
        }
        logger.debug("Method getThreeMovieIds returned = {}", movieIds);

        return movieIds;
    }

    public List<Movie> getThreeMovies(Set<Integer> movieIds) {
        logger.debug("Method getThreeMovies has started");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieIds", movieIds);

        List<Movie> movieList = namedParameterJdbcTemplate.query(getThreeMovies, sqlParameterSource, movieMapper);
        List<Pair<Integer, Country>> countryMapList = namedParameterJdbcTemplate.query(getCountryByThreeMovieId, sqlParameterSource, movieCountryMapper);
        List<Pair<Integer, Genre>> genreMapList = namedParameterJdbcTemplate.query(getGenreByThreeMovieId, sqlParameterSource, movieGenreMapper);


        for (Movie movie : movieList) {
            List<Country> countryList = new ArrayList<>();
            for (Pair<Integer, Country> movieCountryMap : countryMapList) {

                    if (movie.getId() == movieCountryMap.getKey()) {
                        countryList.add(movieCountryMap.getValue());
                    }
            }
            movie.setCountries(countryList);

            List<Genre> genreList = new ArrayList<>();

            for (Pair<Integer, Genre> movieGenreMap : genreMapList) {
                    if (movie.getId() == movieGenreMap.getKey()) {
                        genreList.add(movieGenreMap.getValue());
                    }
            }
            movie.setGenres(genreList);
        }

        logger.debug("Method getThreeMovies returned = {}", movieList);

        return movieList;
    }

    @Override
    public List<Movie> getMovieByGenreId(int genreId) {
        logger.debug("Method getMovieByGenreId is started.");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("genreId", genreId);

        List<Movie> movies = namedParameterJdbcTemplate.query(getMovieByGenreId, sqlParameterSource, movieMapper);

        logger.debug("Method getMovieByGenreId returned = {}", movies);
        return movies;
    }

    @Override
    public List<Movie> getAllMoviesSorted(String field, Sorting direction) {
        logger.debug("Sorting movies by rating is started.");
        List<Movie> movieList = jdbcTemplate.query(QueryBuilder.getSortedSQL(getAllMovies, field, direction), movieMapper);
        logger.debug("Sorting by rating returned = {}", movieList);
        return movieList;
    }

    @Override
    public List<Movie> getMoviesByGenreSorted(int genreId, String field, Sorting direction) {
        logger.debug("Sorting movies by rating is started.");
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("genreId", genreId);
        List<Movie> movieList = namedParameterJdbcTemplate.query(QueryBuilder.getSortedSQL(getMovieByGenreId, field, direction), sqlParameterSource, movieMapper);
        logger.debug("Sorting by rating returned = {}", movieList);
        return movieList;
    }


}
