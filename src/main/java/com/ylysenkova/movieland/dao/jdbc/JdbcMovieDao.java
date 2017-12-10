package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.MovieDao;
import com.ylysenkova.movieland.dao.jdbc.utils.QueryBuilder;
import com.ylysenkova.movieland.dao.mapper.MovieMapper;
import com.ylysenkova.movieland.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.util.*;


@Repository
public class JdbcMovieDao implements MovieDao {

    private final MovieMapper movieMapper = new MovieMapper();
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Random random = new Random();

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private String getAllMoviesSQL;
    @Autowired
    private String getThreeMoviesSQL;
    @Autowired
    private String getMovieCountSQL;
    @Autowired
    private String getMovieByGenreIdSQL;
    @Autowired
    private String getMovieByIdSQL;
    @Autowired
    private String insertMovieSQL;
    @Autowired
    private String insertMovieGenreSQL;
    @Autowired
    private String insertMovieCountrySQL;
    @Autowired
    private String updateMovieSQL;

    @Override
    public List<Movie> getAll() {
        logger.debug("Method getAll has started");
        List<Movie> moviesList = jdbcTemplate.query(getAllMoviesSQL, movieMapper);
        logger.debug("Method getAll returned = {}", moviesList);
        return moviesList;
    }
    @Override
    public Set<Integer> getThreeMovieIds() {
        logger.debug("Method getThreeMovieIds has started");
        Set<Integer> movieIds = new HashSet<>();

        int movieCount = jdbcTemplate.queryForObject(getMovieCountSQL, Integer.class);
        int searchCount = 0;

        if (movieCount < 3) {
            searchCount = movieCount;
        } else {
            searchCount = 3;
        }
        while (movieIds.size() < searchCount) {
            movieIds.add(random.nextInt(movieCount) + 1);
        }
        logger.debug("Method getThreeMovieIds returned = {}", movieIds);

        return movieIds;
    }

    public List<Movie> getThreeMovies(Set<Integer> movieIds) {
        logger.debug("Method getThreeMoviesSQL has started");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieIds", movieIds);

        List<Movie> movieList = namedParameterJdbcTemplate.query(getThreeMoviesSQL, sqlParameterSource, movieMapper);

        logger.debug("Method getThreeMoviesSQL returned = {}", movieList);

        return movieList;
    }

    @Override
    public List<Movie> getMovieByGenreId(int genreId) {
        logger.debug("Method getMovieByGenreIdSQL is started.");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("genreId", genreId);

        List<Movie> movies = namedParameterJdbcTemplate.query(getMovieByGenreIdSQL, sqlParameterSource, movieMapper);

        logger.debug("Method getMovieByGenreIdSQL returned = {}", movies);
        return movies;
    }

    @Override
    public Movie getMovieById(int movieId) {
        logger.debug("Method getMovieByIdSQL is started.");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieId", movieId);

        Movie movie = namedParameterJdbcTemplate.queryForObject(getMovieByIdSQL, sqlParameterSource, movieMapper);

        logger.debug("Method getMovieByIdSQL returns = {}", movie);

        return movie;
    }


    @Override
    public List<Movie> getAllMoviesSorted(String field, Sorting direction) {
        logger.debug("Sorting movies by rating is started.");
        List<Movie> movieList = jdbcTemplate.query(QueryBuilder.getSortedSQL(getAllMoviesSQL, field, direction), movieMapper);
        logger.debug("Sorting by rating returned = {}", movieList);
        return movieList;
    }

    @Override
    public List<Movie> getMoviesByGenreSorted(int genreId, String field, Sorting direction) {
        logger.debug("Sorting movies by rating is started.");
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("genreId", genreId);
        List<Movie> movieList = namedParameterJdbcTemplate.query(QueryBuilder.getSortedSQL(getMovieByGenreIdSQL, field, direction), sqlParameterSource, movieMapper);
        logger.debug("Sorting by rating returned = {}", movieList);
        return movieList;
    }

    @Override
    public void addMovie(Movie movie) {

        logger.info("Inserting movie ={} is started.", movie);

        KeyHolder generatedMovieId = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("nameRussian", movie.getNameRussian());
        sqlParameterSource.addValue("nameNative", movie.getNameNative());
        sqlParameterSource.addValue("yearOfRelease", movie.getYearOfRelease());
        sqlParameterSource.addValue("description", movie.getDescription());
        sqlParameterSource.addValue("rating", movie.getRating());
        sqlParameterSource.addValue("price", movie.getPrice());
        sqlParameterSource.addValue("picturePath", movie.getPicturePath());
        namedParameterJdbcTemplate.update(insertMovieSQL, sqlParameterSource, generatedMovieId);
        for (Genre genre : movie.getGenres()) {
            sqlParameterSource.addValue("movieId", generatedMovieId.getKey());
            sqlParameterSource.addValue("genreId", genre.getId());
            namedParameterJdbcTemplate.update(insertMovieGenreSQL, sqlParameterSource);
        }
        for (Country country : movie.getCountries()) {
            sqlParameterSource.addValue("movieId", generatedMovieId.getKey());
            sqlParameterSource.addValue("countryId", country.getId());
            namedParameterJdbcTemplate.update(insertMovieCountrySQL, sqlParameterSource);
        }
    }

    @Override
    public void editMovie(Movie movie) {
        KeyHolder generatedMovieId = new GeneratedKeyHolder();
        logger.info("Movie with movie id ={} is updating", movie.getId());
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieId", movie.getId());
        sqlParameterSource.addValue("nameRussian", movie.getNameRussian());
        sqlParameterSource.addValue("nameNative", movie.getNameNative());
        sqlParameterSource.addValue("yearOfRelease", movie.getYearOfRelease());
        sqlParameterSource.addValue("description", movie.getDescription());
        sqlParameterSource.addValue("rating", movie.getRating());
        sqlParameterSource.addValue("price", movie.getPrice());
        sqlParameterSource.addValue("picturePath", movie.getPicturePath());
        namedParameterJdbcTemplate.update(updateMovieSQL, sqlParameterSource);
        for (Genre genre : movie.getGenres()) {
            sqlParameterSource.addValue("movieId", generatedMovieId.getKey());
            sqlParameterSource.addValue("genreId", genre.getId());
            namedParameterJdbcTemplate.update(insertMovieGenreSQL, sqlParameterSource);
        }
        for (Country country : movie.getCountries()) {
            sqlParameterSource.addValue("movieId", generatedMovieId.getKey());
            sqlParameterSource.addValue("countryId", country.getId());
            namedParameterJdbcTemplate.update(insertMovieCountrySQL, sqlParameterSource);
        }
    }


}
