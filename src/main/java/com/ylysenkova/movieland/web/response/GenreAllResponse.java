package com.ylysenkova.movieland.web.response;

import com.ylysenkova.movieland.model.Genre;

public class GenreAllResponse {

    private int id;
    private String name;

    public GenreAllResponse() {
    }

    public GenreAllResponse(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
