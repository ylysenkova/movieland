package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.CountryDao;
import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Override
    public void enrichMoviesWithCountries(List<Movie> movieList) {
        countryDao.enrichMoviesWithCountries(movieList);
    }

    @Override
    public void enrichMovieWithCountries(Movie movie) {
        countryDao.enrichMovieWithCountries(movie);
    }

    @Override
    public List<Country> getAll() {
        return countryDao.getAll();
    }

    @Override
    public void removeCountryMovieLink(Movie movie) {
        countryDao.removeCountryMovieLink(movie);
    }

    @Override
    public void editAddCountry(Movie movie) {
        countryDao.editAddCountry(movie);
    }
}
