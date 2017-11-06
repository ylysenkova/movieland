package com.ylysenkova.movieland.dao.jdbc.impl;

import com.ylysenkova.movieland.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-test-config.xml"})
public class JdbcMovieDaoImplTest {
@Autowired
private JdbcMovieDaoImpl jdbcMovieDao;

    @Test
    public void getAllMovies() throws Exception {
        List<Movie> movies = jdbcMovieDao.getAllMovies();
        assertEquals(25, movies.size());
    }

    @Test
    public void getThreeMovies() throws Exception {
        List<Movie> threeMovies = jdbcMovieDao.getThreeMovies(jdbcMovieDao.getThreeMovieIds());
        assertEquals(3, threeMovies.size());

    }

}