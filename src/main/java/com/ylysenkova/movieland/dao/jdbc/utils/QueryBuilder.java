package com.ylysenkova.movieland.dao.jdbc.utils;

import com.ylysenkova.movieland.model.Sorting;

public class QueryBuilder {

    public static String getSortedSQL(String query,  String field, Sorting direction) {
        StringBuilder sortingBuilder = new StringBuilder(query);
        sortingBuilder.deleteCharAt(sortingBuilder.indexOf(";"));
        sortingBuilder.append(query);
        sortingBuilder.append(" ");
        sortingBuilder.append("ORDER BY ");
        sortingBuilder.append(field);
        sortingBuilder.append(" ");
        sortingBuilder.append(direction);
        sortingBuilder.append(";");
        return sortingBuilder.toString();
    }
}
