package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.GenreDao;
import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.dao.mapper.GenreMapper;
import com.ylysenkova.movieland.dao.mapper.MovieGenreMapper;
import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository(value = "jdbcGenreDao")
public class JdbcGenreDao implements GenreDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final GenreMapper genreMapper = new GenreMapper();
    private final MovieGenreMapper movieGenreMapper = new MovieGenreMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private String getAllGenres;
    @Autowired
    private String getGenreByThreeMovieId;
    @Autowired
    private String getGenreByMovieId;

    @Override
    public List<Genre> getAll() {
        logger.debug("Method getAll is started.");
        List<Genre> genres = jdbcTemplate.query(getAllGenres, genreMapper);
        logger.debug("Method getAll returned = {}", genres);
        return genres;
    }


    @Override
    public void enrichMoviesWithGenres(List<Movie> movieList) {
        logger.debug("Enrichment movies with genres is started.");

        Set<Integer> movieIds = new HashSet<>();
        for (Movie movie : movieList) {
            movieIds.add(movie.getId());
        }
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieIds", movieIds);

        List<Pair<Integer, Genre>> genreMapList = namedParameterJdbcTemplate.query(getGenreByThreeMovieId, sqlParameterSource, movieGenreMapper);

        for (Movie movie : movieList) {

            List<Genre> genreList = new ArrayList<>();

            for (Pair<Integer, Genre> movieGenreMap : genreMapList) {
                if (movie.getId() == movieGenreMap.getKey()) {
                    genreList.add(movieGenreMap.getValue());
                }
            }
            movie.setGenres(genreList);
        }
    }

    public void enrichMovieWithGenres(Movie movie) {
        logger.debug("Enrichment movies with genres is started.");

        int movieId = movie.getId();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieId", movieId);

        List<Pair<Integer, Genre>> genreMapList = namedParameterJdbcTemplate.query(getGenreByMovieId, sqlParameterSource, movieGenreMapper);

        List<Genre> genreList = new ArrayList<>();

        for (Pair<Integer, Genre> movieGenreMap : genreMapList) {
            if (movie.getId() == movieGenreMap.getKey()) {
                genreList.add(movieGenreMap.getValue());
            }
        }
        movie.setGenres(genreList);
    }
}
