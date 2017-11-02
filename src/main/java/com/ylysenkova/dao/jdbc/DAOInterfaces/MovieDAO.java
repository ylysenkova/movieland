package com.ylysenkova.dao.jdbc.DAOInterfaces;

import com.ylysenkova.model.Movie;

import java.util.List;

/**
 * Created by dp-ptcstd-47 on 10/30/2017.
 */
public interface MovieDAO {

    public  List<Movie> getAllMovies();
}
