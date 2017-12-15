package com.ylysenkova.movieland.service;

import com.ylysenkova.movieland.model.Sorting;

public interface SortingValidationService {

    void allowOnlyRatingOrPriceSorting (Sorting ratingSortDirection, Sorting priceSortDirection);
    void checkSortingForRating(Sorting direction);
}
