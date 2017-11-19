package com.ylysenkova.movieland.dao;


import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface ReviewDao {

    void enrichMovieWithReviews (Movie movieList);
}
