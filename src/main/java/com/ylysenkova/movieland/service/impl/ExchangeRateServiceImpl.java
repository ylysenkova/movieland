package com.ylysenkova.movieland.service.impl;


import com.ylysenkova.movieland.model.Currency;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Rate;
import com.ylysenkova.movieland.service.ExchangeRateService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Value("${rate.nbu.url}")
    private String url;
    private SimpleDateFormat format;
    private final BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
    private Logger logger = LoggerFactory.getLogger(getClass());
    private RestTemplate template = new RestTemplate();

    public void exchangeCurrency (Currency currencyTo, Movie movie) {
        Date date  = new Date();
        format = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = format.format(date);
        Security.insertProviderAt(bouncyCastleProvider, 1);
        UriComponents buildedUri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("valcode", currencyTo)
                .queryParam("date", formattedDate)
                .queryParam("json")
                .build();
        Rate[] rate = template.getForObject(buildedUri.toUri(), Rate[].class);
            if (rate.length!=0) {
                movie.setPrice((BigDecimal.valueOf(movie.getPrice()).divide(rate[0].getRate(), BigDecimal.ROUND_HALF_UP)).doubleValue());
            }
            else {
                logger.error("The error occurs during the executing exchangeRate method");
                throw new RuntimeException("There is no rate for currency " + currencyTo);
            }



    }
}
