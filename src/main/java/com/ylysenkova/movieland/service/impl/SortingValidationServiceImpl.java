package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.model.Sorting;
import com.ylysenkova.movieland.service.SortingValidationService;
import org.springframework.stereotype.Service;

@Service
public class SortingValidationServiceImpl implements SortingValidationService {

    public void allowOnlyRatingOrPriceSorting(Sorting ratingSortDirection, Sorting priceSortDirection) {
        if (ratingSortDirection != null && priceSortDirection != null) {
            throw new RuntimeException("Only one sorting parameter is allowed");
        }
    }

    public void checkSortingForRating(Sorting direction) {
        if (direction == Sorting.ASC) {
            throw new RuntimeException("ASC is not allowed for Rating");
        }
    }
}
