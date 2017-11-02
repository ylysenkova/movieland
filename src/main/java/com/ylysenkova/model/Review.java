package com.ylysenkova.model;

/**
 * Created by dp-ptcstd-47 on 10/30/2017.
 */
public class Review {

    private int reviewId;
    private int userId;
    private String reviewText;

    public Review() {
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", userId=" + userId +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}
