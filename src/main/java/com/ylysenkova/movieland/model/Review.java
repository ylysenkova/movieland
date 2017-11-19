package com.ylysenkova.movieland.model;

public class Review {

    private int id;
    private User users;
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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", users=" + users +
                ", text='" + text + '\'' +
                '}';
    }
}
