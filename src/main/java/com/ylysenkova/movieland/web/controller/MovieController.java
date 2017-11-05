package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.impl.MovieServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieServiceImpl movieService;

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public @ResponseBody List<Movie> getAllMovies() {
        logger.debug("Sending request...");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = movieService.getAllMovies();
        logger.debug("Movie {} is received. It took {} ms", movies, System.currentTimeMillis() - startTime);
        return movies;
    }

    @RequestMapping(value = "/movie/random", method = RequestMethod.GET)
    public @ResponseBody List<Movie> getThreeMovies() {
        logger.debug("Sending request for 3 movies...");
        long startTime = System.currentTimeMillis();
        List<Movie> movieList = movieService.getThreeMovies();
        logger.debug("Movie {} is received. It took {} ms", movieList, System.currentTimeMillis()-startTime );
        return movieList;
    }

}
