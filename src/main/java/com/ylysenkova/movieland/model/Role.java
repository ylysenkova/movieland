package com.ylysenkova.movieland.model;


public enum Role {
    USER("user"),
    ADMIN("admin");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role getRole(String role) {
        for (Role roles : values()) {
            if (roles.value.equalsIgnoreCase(role)) {
                return roles;
            }

        }
        throw new RuntimeException("Incorrect role.");
    }
}
