package com.ylysenkova.movieland.web.converter;

import com.ylysenkova.movieland.model.Sorting;

import java.beans.PropertyEditorSupport;

public class SortingConvertor extends PropertyEditorSupport{

    @Override
    public void setAsText (String text) throws IllegalArgumentException {
        setValue(Sorting.getSorting(text));
    }
}
