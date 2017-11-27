package com.ylysenkova.movieland.web.dto.request;


public class SaveReviewRequest {

    private int movieId;
    private String text;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SaveReviewRequest{" +
                "movieId=" + movieId +
                ", text='" + text + '\'' +
                '}';
    }
}
