package hu.bozgab.movie.service;

import hu.bozgab.movie.domain.Movie;

import java.util.List;

public interface MovieManagementService {

    List<Movie> findAll();
}
