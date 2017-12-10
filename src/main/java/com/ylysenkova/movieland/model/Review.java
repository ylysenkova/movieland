package com.ylysenkova.movieland.model;

public class Review {

    private int id;
    private User user;
    private int movieId;
    private String text;

    public Review() {
    }

    public Review(Review review) {
        id = review.id;
        text = review.text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", user=" + user +
                ", movieId=" + movieId +
                ", text='" + text + '\'' +
                '}';
    }
}
