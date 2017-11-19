package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.CountryDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryDao countryDao;
    @Override
    public void enrichMovieWithCountries(List<Movie> movieList) {
        countryDao.enrichMovieWithCountries(movieList);
    }
}
