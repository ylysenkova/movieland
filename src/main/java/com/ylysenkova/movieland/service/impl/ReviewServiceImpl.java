package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.ReviewDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Review;
import com.ylysenkova.movieland.service.AuthenticationService;
import com.ylysenkova.movieland.service.ReviewService;
import com.ylysenkova.movieland.web.exceptions.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public void enrichMovieWithReview(Movie movie) {
        reviewDao.enrichMovieWithReviews(movie);

    }

    @Override
    public void addReview(Review review) {
        int userId;
        userId = reviewDao.getUserByReview(review).getId();
        reviewDao.addReview(review.getMovie().getId(), review.getText(), userId);
        logger.info("Method addReview Gets movieId ={}, review text ={}, review text ={}", review.getMovie().getId(), review.getText(), userId);

    }
}
