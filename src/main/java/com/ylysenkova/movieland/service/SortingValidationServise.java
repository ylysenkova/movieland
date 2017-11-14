package com.ylysenkova.movieland.service;

import com.ylysenkova.movieland.model.Sorting;

public interface SortingValidationServise {

    void allowOnlyRatingOrPriceSorting (String ratingSortDirection, String priceSortDirection);
    void checkSortingForRating(Sorting direction);
}
