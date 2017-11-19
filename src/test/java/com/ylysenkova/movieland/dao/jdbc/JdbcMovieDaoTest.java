package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.MovieDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-test-config.xml"})
public class JdbcMovieDaoTest {

    @Autowired
    private MovieDao movieDao;

    @Test
    public void getAllMovies() throws Exception {
        List<Movie> movies = movieDao.getAll();
        assertEquals(25, movies.size());
    }

    @Test
    public void getMovieById() throws Exception {
        Movie movieById = movieDao.getMovieById(3);
        assertEquals(3, movieById.getId());
    }

    @Test
    public void getThreeMovies() throws Exception {
        List<Movie> threeMovies = movieDao.getThreeMovies(movieDao.getThreeMovieIds());
        assertEquals(3, threeMovies.size());

    }

    @Test
    public void getMovieCountByGenreId() throws Exception {
        List<Movie> movieByGenreId = movieDao.getMovieByGenreId(2);
        assertEquals(7, movieByGenreId.size());
    }

    @Test
    public void getGenreSortingByRating() throws Exception {

        List<Movie> movieByGenreSortByRating = movieDao.getMoviesByGenreSorted(3, "rating", Sorting.DESC);
        assertEquals(2, movieByGenreSortByRating.get(0).getId(), 0);
        assertEquals(9, movieByGenreSortByRating.get(5).getId(), 0);
    }

    @Test
    public void getGenreSortingByPrice() throws Exception {

        List<Movie> movieByGenreSortByPrice = movieDao.getMoviesByGenreSorted(3, "price",Sorting.DESC);
        assertEquals(9, movieByGenreSortByPrice.get(0).getId(), 0);
        assertEquals(14, movieByGenreSortByPrice.get(5).getId(), 0);


        movieByGenreSortByPrice = movieDao.getMoviesByGenreSorted(4, "price",Sorting.ASC);
        assertEquals(6, movieByGenreSortByPrice.get(0).getId(), 0);
        assertEquals(19, movieByGenreSortByPrice.get(2).getId(), 0);

    }

    @Test
    public void getSortingByRating() throws Exception {

        List<Movie> movieSortByRating = movieDao.getAllMoviesSorted("rating", Sorting.DESC);
        assertEquals(8.9, movieSortByRating.get(0).getRating(), 0);
        assertEquals(7.6, movieSortByRating.get(24).getRating(), 0);
    }

    @Test
    public void getSortingByPrice() throws Exception {

        List<Movie> movieSortByPrice = movieDao.getAllMoviesSorted("price",Sorting.DESC);
        assertEquals(BigDecimal.valueOf(200.6).compareTo(movieSortByPrice.get(0).getPrice()), 0);
        assertEquals(BigDecimal.valueOf(100).compareTo(movieSortByPrice.get(24).getPrice()), 0);

        movieSortByPrice = movieDao.getAllMoviesSorted("price",Sorting.ASC);
        assertEquals(BigDecimal.valueOf(100).compareTo(movieSortByPrice.get(0).getPrice()), 0);
        assertEquals(BigDecimal.valueOf(200.6).compareTo(movieSortByPrice.get(24).getPrice()), 0);

    }

}