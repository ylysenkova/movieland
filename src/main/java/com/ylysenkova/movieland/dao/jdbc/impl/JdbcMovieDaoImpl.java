package com.ylysenkova.movieland.dao.jdbc.impl;

import com.ylysenkova.movieland.dao.DAOInterface.MovieDao;
import com.ylysenkova.movieland.dao.mapper.MovieCountryMapper;
import com.ylysenkova.movieland.dao.mapper.MovieGenreMapper;
import com.ylysenkova.movieland.dao.mapper.MovieMapper;
import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcMovieDaoImpl implements MovieDao {

private final MovieMapper movieMapper = new MovieMapper();
private final MovieCountryMapper movieCountryMapper = new MovieCountryMapper();
private final MovieGenreMapper movieGenreMapper = new MovieGenreMapper();

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

    @Override
    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(getAllMovies,  movieMapper);
    }

    @Override
    public Set<Integer> getThreeMovieIds() {

    Set<Integer> movieIds = new HashSet<>();
    Random random = new Random();

    int movieCount = jdbcTemplate.queryForObject(getMovieCount, Integer.class);
    int serchCount = 0;

    if (movieCount<3) {
        serchCount = movieCount;
    }
    else {
        serchCount = 3;
    }
    while (movieIds.size()<serchCount) {
         movieIds.add(random.nextInt(movieCount)+1);
    }

    return movieIds;
    }

    public List<Movie> getThreeMovies(Set<Integer> movieIds){

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieIds", movieIds);

        List<Movie> movieList = namedParameterJdbcTemplate.query(getThreeMovies, sqlParameterSource, movieMapper);
        List<Pair<Integer, Country>> countryMapList = namedParameterJdbcTemplate.query(getCountryByThreeMovieId, sqlParameterSource, movieCountryMapper);
        List<Pair<Integer, Genre>> genreMapList = namedParameterJdbcTemplate.query(getGenreByThreeMovieId, sqlParameterSource, movieGenreMapper);


        for (Movie movie : movieList) {
            List<Country> countryList = new ArrayList<>();
            for (Pair<Integer, Country> movieCountryMap : countryMapList) {
                if(movie.getId()==movieCountryMap.getKey()) {
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



    return movieList;
    }
}
