package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.web.converter.SortingConvertor;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;
import com.ylysenkova.movieland.service.impl.MovieServiceImpl;
import com.ylysenkova.movieland.service.impl.SortingValidationServiceImpl;
import com.ylysenkova.movieland.web.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieServiceImpl movieService;
    @Autowired
    private SortingValidationServiceImpl sortingValidationService;


    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getAll(
            @RequestParam(value = "rating", required = false) Sorting ratingSortDirection,
            @RequestParam(value = "price", required = false) Sorting priceSortDirection
    ) {
        try {
            sortingValidationService.allowOnlyRatingOrPriceSorting(ratingSortDirection, priceSortDirection);

            logger.debug("Sending response...");
            long startTime = System.currentTimeMillis();
            List<MovieAllResponse> movieAllResponses = new ArrayList<>();
            List<Movie> movies;
            if (ratingSortDirection != null) {
                sortingValidationService.checkSortingForRating(ratingSortDirection);
                movies = movieService.getAllSorted("rating", ratingSortDirection);
            } else if (priceSortDirection != null) {
                movies = movieService.getAllSorted("price", priceSortDirection);
            } else {
                movies = movieService.getAll();
            }

            for (Movie movie : movies) {
                movieAllResponses.add(new MovieAllResponse(movie));
            }
            logger.debug("Movie {} is received. It took {} ms", movieAllResponses, System.currentTimeMillis() - startTime);
            return new ResponseEntity<List<MovieAllResponse>>(movieAllResponses, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/movie/random", method = RequestMethod.GET)
    public
    @ResponseBody
    List<MovieRandomResponse> getThreeMovies() {
        logger.debug("Sending request for 3 movies...");
        long startTime = System.currentTimeMillis();
        List<Movie> movieList = movieService.getThreeMovies();
        List<MovieRandomResponse> movieRandomResponses = new ArrayList<>();
        for (Movie movie : movieList) {
            movieRandomResponses.add(new MovieRandomResponse(movie));
        }
        logger.debug("Movie {} is received. It took {} ms", movieList, System.currentTimeMillis() - startTime);
        return movieRandomResponses;
    }

    @RequestMapping(value = "/movie/genre/{genreId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getMovieByGenreId(
            @PathVariable(value = "genreId") int genreId,
            @RequestParam(value = "rating", required = false) Sorting ratingSortDirection,
            @RequestParam(value = "price", required = false) Sorting priceSortDirection
    ) {
        try {
            sortingValidationService.allowOnlyRatingOrPriceSorting(ratingSortDirection, priceSortDirection);

            logger.debug("Sending request...");
            long startTime = System.currentTimeMillis();
            List<Movie> movies;
            List<MovieResponseByGenre> movieResponseByGenres = new ArrayList<>();
            if (ratingSortDirection != null) {
                sortingValidationService.checkSortingForRating(ratingSortDirection);
                movies = movieService.getMoviesByGenreSorted(genreId, "rating", ratingSortDirection);
            } else if (priceSortDirection != null) {
                movies = movieService.getMoviesByGenreSorted(genreId, "price", priceSortDirection);
            } else {
                movies = movieService.getMovieByGenreId(genreId);
            }
            for (Movie movie : movies) {
                movieResponseByGenres.add(new MovieResponseByGenre(movie));
            }
            logger.debug("Movie {} is received.It took {} ms ", movies, System.currentTimeMillis() - startTime);
            return new ResponseEntity<List<MovieResponseByGenre>>(movieResponseByGenres, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getMovieById (@PathVariable(value = "movieId") int movieId) {
        logger.debug("Sending request ... ");
        long startTime = System.currentTimeMillis();
        Movie movie = movieService.getMovieById(movieId);
        MovieWithReviewResponse  movieWithReviewResponse = new MovieWithReviewResponse(movie);

        logger.debug("Movie {} is received.It took {} ms", movie, System.currentTimeMillis() - startTime);
        return new ResponseEntity<MovieWithReviewResponse>(movieWithReviewResponse, HttpStatus.OK);
    }

    @InitBinder
    public void initBinder (WebDataBinder binder) {
        binder.registerCustomEditor(Sorting.class, new SortingConvertor());
    }


}
