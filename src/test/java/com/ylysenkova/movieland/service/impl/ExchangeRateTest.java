package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.config.RateConfig;
import com.ylysenkova.movieland.model.Currency;
import com.ylysenkova.movieland.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RateConfig.class)
public class ExchangeRateTest {

    @Autowired
    ExchangeRateServiceImpl exchangeRate;
    @Test
    public void exchangeCurrency() throws Exception {
        Movie movie = new Movie();
        movie.setPrice(122.99);
        exchangeRate.exchangeCurrency(Currency.USD, movie);
        double ExpectedPrice = (BigDecimal.valueOf(122.99).divide(BigDecimal.valueOf(26.492211), BigDecimal.ROUND_HALF_UP)).doubleValue();
        assertEquals(ExpectedPrice, movie.getPrice(), 0);

    }

}