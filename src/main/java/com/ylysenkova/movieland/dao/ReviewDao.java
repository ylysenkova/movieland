package com.ylysenkova.movieland.dao;


import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Review;
import com.ylysenkova.movieland.model.User;

import java.util.List;

public interface ReviewDao {

    void enrichMovieWithReviews(Movie movieList);

    void addReview(int movieId, String text, int userId);

    User getUserByReview(Review review);
}
