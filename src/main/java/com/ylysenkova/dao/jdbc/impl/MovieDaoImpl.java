package com.ylysenkova.dao.jdbc.impl;

import com.ylysenkova.dao.mapper.MovieMapper;
import com.ylysenkova.dao.jdbc.DAOInterfaces.MovieDAO;
import com.ylysenkova.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dp-ptcstd-47 on 10/30/2017.
 */
@Repository
public class MovieDaoImpl implements MovieDAO{

    private static final String GET_ALL_MOVIES = "select id, name_Russian, name_Native, year_Of_Release, rating, price, picture_Path" +
            "from movie;";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Movie> getAllMovies() {
        MovieMapper movieMapper = new MovieMapper();
        return jdbcTemplate.query(GET_ALL_MOVIES,  movieMapper);
    }
}
