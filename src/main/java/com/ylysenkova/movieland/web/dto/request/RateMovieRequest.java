package com.ylysenkova.movieland.web.dto.request;


import com.ylysenkova.movieland.model.User;

public class RateMovieRequest {
    private int movieId;
    private double rating;
    private User user;

    public RateMovieRequest() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RateMovieRequest{" +
                "movieId=" + movieId +
                ", rating=" + rating +
                ", user=" + user +
                '}';
    }
}
