package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.ReviewDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public void enrichMovieWithReview(Movie movie) {
        reviewDao.enrichMovieWithReviews(movie);

    }
}
