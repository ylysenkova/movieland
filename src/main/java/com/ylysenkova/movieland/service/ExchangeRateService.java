package com.ylysenkova.movieland.service;


import com.ylysenkova.movieland.model.Currency;
import com.ylysenkova.movieland.model.Movie;

public interface ExchangeRateService {

    void exchangeCurrency (Currency currencyTo, Movie movie);
}
