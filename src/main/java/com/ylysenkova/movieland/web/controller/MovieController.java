package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.*;
import com.ylysenkova.movieland.security.Protected;
import com.ylysenkova.movieland.service.ExchangeRateService;
import com.ylysenkova.movieland.service.MovieService;
import com.ylysenkova.movieland.service.SortingValidationService;
import com.ylysenkova.movieland.web.converter.CurrencyConvertot;
import com.ylysenkova.movieland.web.converter.SortingConvertor;
import com.ylysenkova.movieland.web.dto.request.SaveMovieRequest;
import com.ylysenkova.movieland.web.dto.response.*;
import com.ylysenkova.movieland.web.util.MovieBuilder;
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
@RequestMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;
    @Autowired
    private SortingValidationService sortingValidationService;
    @Autowired
    private ExchangeRateService exchangeRate;


    @RequestMapping(method = RequestMethod.GET)
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

    @RequestMapping(value = "/random", method = RequestMethod.GET)
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

    @RequestMapping(value = "/genre/{genreId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getMovieByGenreId(
            @PathVariable(value = "genreId") int genreId,
            @RequestParam(value = "rating", required = false) Sorting ratingSortDirection,
            @RequestParam(value = "price", required = false) Sorting priceSortDirection
    ) {
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
    }

    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getMovieById(
            @PathVariable(value = "movieId") int movieId,
            @RequestParam(value = "currency", required = false) Currency currency) {
        logger.debug("Sending request ... ");
        long startTime = System.currentTimeMillis();
        Movie movie = movieService.getMovieById(movieId);
        MovieWithReviewResponse movieWithReviewResponse;
        if (currency != null) {
            exchangeRate.exchangeCurrency(currency, movie);
        }
        movieWithReviewResponse = new MovieWithReviewResponse(movie);

        logger.debug("Movie {} is received.It took {} ms", movieWithReviewResponse, System.currentTimeMillis() - startTime);
        return new ResponseEntity<MovieWithReviewResponse>(movieWithReviewResponse, HttpStatus.OK);

    }

    @Protected(value = Role.ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addMovie(@RequestBody SaveMovieRequest saveMovieRequest) {
        logger.info("Administrator is adding movie ={}", saveMovieRequest.getNameNative());

        Movie movie = MovieBuilder.fromMovieRequest(saveMovieRequest).getMovie().build();

        movieService.addMovie(movie);
    }

    @Protected(value = Role.ADMIN)
    @RequestMapping(value = "/{movieId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editMovie(
            @RequestBody SaveMovieRequest saveMovieRequest) {
        logger.info("User with role ={} starts edit movie={}", Role.ADMIN, saveMovieRequest);

        Movie movie = MovieBuilder.fromMovieRequest(saveMovieRequest).getMovie().build();

        logger.info("Movie with id ={} is processing", movie.getId());
        movieService.editMovie(movie);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Sorting.class, new SortingConvertor());
        binder.registerCustomEditor(Currency.class, new CurrencyConvertot());
    }


}
