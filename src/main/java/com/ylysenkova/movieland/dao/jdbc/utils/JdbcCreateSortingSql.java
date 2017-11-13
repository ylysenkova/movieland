package com.ylysenkova.movieland.dao.jdbc.utils;

import com.ylysenkova.movieland.model.Sorting;

public class JdbcCreateSortingSql {

    public static String getSortedSQL(String query,  String field, Sorting direction) {
        StringBuilder sb = new StringBuilder();
        sb.append(query.split(";")[0]);
        sb.append(" ");
        sb.append("ORDER BY ");
        sb.append(field);
        sb.append(" ");
        sb.append(direction);
        sb.append(";");
        return sb.toString();
    }
}
