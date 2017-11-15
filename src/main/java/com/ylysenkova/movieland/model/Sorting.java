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

    public static Sorting getSorting(Sorting direction) {
        for (Sorting sorting : values()) {
            if(sorting.value.equalsIgnoreCase(direction.toString())) {
                return sorting;
            }
        }
        throw new RuntimeException("Incorrect sorting value");
    }
}
