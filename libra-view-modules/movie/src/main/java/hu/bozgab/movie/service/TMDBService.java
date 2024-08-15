package hu.bozgab.movie.service;

import hu.bozgab.movie.dto.GenreDTO;

import java.util.List;

public interface TMDBService {

    List<GenreDTO> updateGenres();

    String findMovie(String searchKey);

}
