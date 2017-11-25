package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.config.RateConfig;
import com.ylysenkova.movieland.model.Currency;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Rate;
import com.ylysenkova.movieland.service.ExchangeRateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = RateConfig.class)
public class ExchangeRateTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @Test
    public void exchangeCurrency() throws Exception {
        ReflectionTestUtils.setField(exchangeRateService, "url", "http://test.com");
        Movie movie = new Movie();
        movie.setPrice(122.99);
        Rate[] rates = new Rate[1];
        Rate rate = new Rate();
        rate.setCurrency(Currency.USD);
        rate.setRate(BigDecimal.valueOf(26.492211));
        rates[0] = rate;

        when(restTemplate.getForObject(any(),any())).thenReturn(rates);
        exchangeRateService.exchangeCurrency(rate.getCurrency(), movie);
        double ExpectedPrice = (BigDecimal.valueOf(122.99).divide(rate.getRate(), BigDecimal.ROUND_HALF_UP)).doubleValue();
        assertEquals(ExpectedPrice, movie.getPrice(), 0);

    }

}