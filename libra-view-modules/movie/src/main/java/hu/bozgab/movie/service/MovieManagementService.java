package hu.bozgab.movie.service;

import hu.bozgab.movie.dto.GenreDTO;

import java.util.List;


public interface MovieManagementService {

    List<GenreDTO> updateGenres();

    List<GenreDTO> availableGenres();

}
