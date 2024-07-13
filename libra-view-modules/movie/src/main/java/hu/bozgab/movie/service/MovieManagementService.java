package hu.bozgab.movie.service;

import hu.bozgab.movie.dto.MovieDTO;

import java.util.List;

public interface MovieManagementService {

    List<MovieDTO> findAll();
}
