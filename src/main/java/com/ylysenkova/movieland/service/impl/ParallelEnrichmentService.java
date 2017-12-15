package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.CountryService;
import com.ylysenkova.movieland.service.GenreService;
import com.ylysenkova.movieland.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ParallelEnrichmentService {

    private long timeOutMillSec = 5000;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    private GenreService genreService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ReviewService reviewService;

    public void parallelEnrichment(Movie movie) {


        long timeMillSec = timeOutMillSec + System.currentTimeMillis();
        List<Future<?>> futureList = new ArrayList<>();
        Future<?> genreTask = executorService.submit(() -> genreService.enrichMovieWithGenres(movie));
        Future<?> countryTask = executorService.submit(() -> countryService.enrichMovieWithCountries(movie));
        Future<?> reviewTask = executorService.submit(() -> reviewService.enrichMovieWithReview(movie));
        futureList.add(genreTask);
        futureList.add(countryTask);
        futureList.add(reviewTask);
        for (Future<?> future : futureList) {
            long timeLeft;
            try {
                if ((timeLeft = timeMillSec - System.currentTimeMillis()) < 0) {
                    timeLeft = 0;
                }
                future.get(timeLeft, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.warn("Enrichment was interrupted.");
            } catch (ExecutionException e) {
                logger.warn("Something wrong with execution.", e.getMessage());
            } catch (TimeoutException e) {
                future.cancel(true);
                logger.warn("5 seconds is finished.", e.getMessage());
            }

        }

    }


}
