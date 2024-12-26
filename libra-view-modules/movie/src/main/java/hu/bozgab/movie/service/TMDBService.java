package hu.bozgab.movie.service;

import hu.bozgab.movie.dto.integration.TMDBGenreDTO;
import hu.bozgab.movie.dto.integration.TMDBSearchMovieRequest;

import java.util.List;


public interface TMDBService {

    List<TMDBGenreDTO> getGenres();

    String searchMovie(TMDBSearchMovieRequest request);

}
