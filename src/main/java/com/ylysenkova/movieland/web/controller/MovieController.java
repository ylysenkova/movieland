package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.impl.MovieServiceImpl;
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

    @Autowired
    private MovieServiceImpl movieService;

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public @ResponseBody List<Movie> getAllMovies() {

        return movieService.getAllMovies();
    }

    @RequestMapping(value = "/movie/random", method = RequestMethod.GET)
    public @ResponseBody List<Movie> getThreeMovies() {
        return movieService.getThreeMovies();
    }

}
