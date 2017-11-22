package com.ylysenkova.movieland.config;

import com.ylysenkova.movieland.service.impl.ExchangeRateServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:property/rate.properties")
public class RateConfig {

    @Bean
    public ExchangeRateServiceImpl exchangeRate() {
        return new ExchangeRateServiceImpl();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
