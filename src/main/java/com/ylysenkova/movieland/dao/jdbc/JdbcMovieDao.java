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
    private String getAllMovies;
    @Autowired
    private String getThreeMovies;
    @Autowired
    private String getMovieCount;
    @Autowired
    private String getMovieByGenreId;
    @Autowired
    private String getMovieById;
    @Autowired
    private String insertMovie;
    @Autowired
    private String insertMovieGenre;
    @Autowired
    private String insertMovieCountry;
    @Autowired
    private String updateMovie;

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
        int searchCount;

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
        logger.debug("Method getThreeMovies has started");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieIds", movieIds);

        List<Movie> movieList = namedParameterJdbcTemplate.query(getThreeMovies, sqlParameterSource, movieMapper);

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
    public Movie getMovieById(int movieId) {
        logger.debug("Method getMovieById is started.");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieId", movieId);

        Movie movie = namedParameterJdbcTemplate.queryForObject(getMovieById, sqlParameterSource, movieMapper);

        logger.debug("Method getMovieById returns = {}", movie);

        return movie;
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
        namedParameterJdbcTemplate.update(insertMovie, sqlParameterSource, generatedMovieId);
        for (Genre genre : movie.getGenres()) {
            sqlParameterSource.addValue("movieId", generatedMovieId.getKey());
            sqlParameterSource.addValue("genreId", genre.getId());
            namedParameterJdbcTemplate.update(insertMovieGenre, sqlParameterSource);
        }
        for (Country country : movie.getCountries()) {
            sqlParameterSource.addValue("movieId", generatedMovieId.getKey());
            sqlParameterSource.addValue("countryId", country.getId());
            namedParameterJdbcTemplate.update(insertMovieCountry, sqlParameterSource);
        }
    }

    @Override
    public void editMovie(Movie movie) {
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
        namedParameterJdbcTemplate.update(updateMovie, sqlParameterSource);
    }


}
