package com.ylysenkova.movieland.model;


public enum Sorting {
    DESC("desc"),
    ASC("asc");

    private final String value;

    Sorting(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
