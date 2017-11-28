package com.ylysenkova.movieland.model;

public class Review {

    private int id;
    private User user;
    private Movie movie;
    private String text;

    public Review() {
    }

    public Review(int id, String reviewText) {
        this.id = id;
        this.text = reviewText;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", user=" + user +
                ", movie=" + movie +
                ", text='" + text + '\'' +
                '}';
    }
}
