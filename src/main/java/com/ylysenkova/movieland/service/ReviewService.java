package com.ylysenkova.movieland.service;


import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    void enrichMovieWithReview(Movie movie);

    void addReview(Movie movie, Review review);
}
