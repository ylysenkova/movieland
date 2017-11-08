package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.jdbc.JdbcMovieDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-test-config.xml"})
public class JdbcMovieDaoTest {


    @Autowired
    private JdbcMovieDao jdbcMovieDao;

    @Test
    public void getAllMovies() throws Exception {
        List<Movie> movies = jdbcMovieDao.getAll();
        assertEquals(25, movies.size());
    }

    @Test
    public void getThreeMovies() throws Exception {
        List<Movie> threeMovies = jdbcMovieDao.getThreeMovies(jdbcMovieDao.getThreeMovieIds());
        assertEquals(3, threeMovies.size());

    }

    @Test
    public void getMovieCountByGenreId() throws Exception {
        List<Movie> movieByGenreId = jdbcMovieDao.getMovieByGenreId(2);
        assertEquals(7, movieByGenreId.size());
    }

    @Test
    public void getSortingByRating() throws Exception {

        List<Movie> movieSortByRating = jdbcMovieDao.getSortingByRating("desc");
        assertEquals(8.9, movieSortByRating.get(0).getRating(), 0);
        assertEquals(7.6, movieSortByRating.get(24).getRating(), 0);
    }

    @Test
    public void getSortingByPrice() throws Exception {

        List<Movie> movieSortByPrice = jdbcMovieDao.getSortingByPrice("desc");
        assertEquals(200.6, movieSortByPrice.get(0).getPrice(), 0);
        assertEquals(100, movieSortByPrice.get(24).getPrice(), 0);

        movieSortByPrice = jdbcMovieDao.getSortingByPrice("asc");
        assertEquals(100, movieSortByPrice.get(0).getPrice(), 0);
        assertEquals(200.6, movieSortByPrice.get(24).getPrice(), 0);

    }

}