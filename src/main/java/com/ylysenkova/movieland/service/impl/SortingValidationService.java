package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.model.Sorting;
import org.springframework.stereotype.Service;

@Service
public class SortingValidationService {

    public void allowOnlyRatingOrPriceSorting (String ratingSortDirection, String priceSortDirection) {
        if(ratingSortDirection !=null && priceSortDirection != null) {
            throw new RuntimeException("Only one sorting parameter is allowed");
        }
    }

    public void allowDescSortingForRating (Sorting direction) {
        if(direction == Sorting.ASC) {
            throw new RuntimeException("ASC is not allowed for Rating");
        }
    }
}
