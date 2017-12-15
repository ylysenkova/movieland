package com.ylysenkova.movieland.config;

import com.ylysenkova.movieland.service.impl.ParallelEnrichmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:property/parallelEnrichment.properties")
public class ParallelEnrichmentConfig {
    @Bean
    ParallelEnrichmentService parallelEnrichmentService() {return new ParallelEnrichmentService();}
}
