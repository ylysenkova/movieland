package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.impl.MovieServiceImpl;
import com.ylysenkova.movieland.web.response.MovieAllResponse;
import com.ylysenkova.movieland.web.response.MovieRandomResponse;
import com.ylysenkova.movieland.web.response.MovieResponseByGenre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieServiceImpl movieService;


    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public @ResponseBody List<MovieAllResponse> getAllMovies() {
        logger.debug("Sending request...");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = movieService.getAllMovies();
        List<MovieAllResponse> movieAllResponses = new ArrayList<>();
        for (Movie movie : movies) {
            movieAllResponses.add(new MovieAllResponse(movie));
        }
        logger.debug("Movie {} is received. It took {} ms", movies, System.currentTimeMillis() - startTime);
        return movieAllResponses;
    }

    @RequestMapping(value = "/movie/random", method = RequestMethod.GET)
    public @ResponseBody List<MovieRandomResponse> getThreeMovies() {
        logger.debug("Sending request for 3 movies...");
        long startTime = System.currentTimeMillis();
        List<Movie> movieList = movieService.getThreeMovies();
        List<MovieRandomResponse> movieRandomResponses = new ArrayList<>();
        for (Movie movie : movieList) {
            movieRandomResponses.add(new MovieRandomResponse(movie));
        }
        logger.debug("Movie {} is received. It took {} ms", movieList, System.currentTimeMillis()-startTime );
        return movieRandomResponses;
    }

    @RequestMapping(value = "movie/{genreId}", method = RequestMethod.GET)
    public @ResponseBody List<MovieResponseByGenre> getMovieByGenreId (@PathVariable int genreId) {
        logger.debug("Sending request...");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = movieService.getMovieByGenreId(genreId);
        List<MovieResponseByGenre> movieResponseByGenres = new ArrayList<>();
        for (Movie movie : movies) {
            movieResponseByGenres.add(new MovieResponseByGenre(movie));
        }
        logger.debug("Movie {} is received.It took {} ms ", movies, System.currentTimeMillis()-startTime);
        return movieResponseByGenres;
    }

}
