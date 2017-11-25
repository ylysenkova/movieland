package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.service.GenreService;
import com.ylysenkova.movieland.service.impl.GenreServiceImpl;
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
@RequestMapping(value = "/genre", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GenreController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenreService genreService;

    @RequestMapping( method = RequestMethod.GET)
    public
    @ResponseBody
    List<Genre> getAll() {
        logger.debug("Sending request...");
        long startTime = System.currentTimeMillis();
        List<Genre> genres = genreService.getAll();
        logger.debug("Genre {} is received.It took {} ms", genres, System.currentTimeMillis() - startTime);
        return genres;
    }
}
