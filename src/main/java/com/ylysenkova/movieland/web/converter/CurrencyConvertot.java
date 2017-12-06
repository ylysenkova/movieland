package com.ylysenkova.movieland.web.converter;


import com.ylysenkova.movieland.model.Currency;

import java.beans.PropertyEditorSupport;

public class CurrencyConvertot extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Currency.getCurrency(text));
    }
}
