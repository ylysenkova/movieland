package com.ylysenkova.movieland.service;


import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface ReviewService {
    void enrichMovieWithReview(Movie movie);
}
