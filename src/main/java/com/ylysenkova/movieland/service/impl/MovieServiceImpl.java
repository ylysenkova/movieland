package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.MovieDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;
import com.ylysenkova.movieland.model.User;
import com.ylysenkova.movieland.service.CountryService;
import com.ylysenkova.movieland.service.GenreService;
import com.ylysenkova.movieland.service.MovieService;
import com.ylysenkova.movieland.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieDao movieDao;
    @Autowired
    private GenreService genreService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ParallelEnrichmentService parallelEnrichmentService;

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> getThreeMovies() {
        logger.info("Service method GetThreeMovie is started.");
        List<Movie> enrichedMovie = new ArrayList<>();
        Set<Integer> threeMovieIds = movieDao.getThreeMovieIds();
        List<Movie> threeMovies = movieDao.getThreeMovies(threeMovieIds);
        enrichedMovie.addAll(threeMovies);
        genreService.enrichMoviesWithGenres(enrichedMovie);
        countryService.enrichMoviesWithCountries(enrichedMovie);
        return enrichedMovie;
    }

    public List<Movie> getMovieByGenreId(int genreId) {
        return movieDao.getMovieByGenreId(genreId);
    }

    @Override
    public Movie getMovieById(int movieId) {
        Movie movieForEnrichment = movieDao.getMovieById(movieId);
        parallelEnrichmentService.parallelEnrichment(movieForEnrichment);
        return movieForEnrichment;
    }

    public List<Movie> getAllSorted(String field, Sorting direction) {
        return movieDao.getAllMoviesSorted(field, direction);
    }

    @Override
    public List<Movie> getMoviesByGenreSorted(int genreId, String field, Sorting direction) {
        return movieDao.getMoviesByGenreSorted(genreId, field, direction);
    }

    @Transactional
    @Override
    public void addMovie(Movie movie) {
        movieDao.addMovie(movie);
        genreService.editAddGenre(movie);
        countryService.editAddCountry(movie);

    }

    @Transactional
    @Override
    public void editMovie(Movie movie) {
        logger.debug("Service editMovie is started");
        genreService.editAddGenre(movie);
        countryService.editAddCountry(movie);
        movieDao.editMovie(movie);
        logger.debug("Service editMovie finished work.");
    }

    @Override
    public void rateMovie(int movieId, double rating, User user) {
        logger.debug("Service rateMovie is started.");

    }


}
