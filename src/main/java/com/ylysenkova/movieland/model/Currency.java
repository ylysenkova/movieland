package com.ylysenkova.movieland.model;


public enum Currency {
    USD("USD"),
    EUR("EUR");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Currency getCurrency(String currency) {
        for (Currency currencies : values()) {
            if (currencies.value.equalsIgnoreCase(currency)) {
                return currencies;
            }
        }
        throw new RuntimeException("Incorrect currency value");
    }
}
