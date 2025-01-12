package hu.bozgab.movie.service;

import hu.bozgab.movie.dto.integration.configuration.TMDBDetailsDTO;
import hu.bozgab.movie.dto.integration.genres.TMDBGenreDTO;
import hu.bozgab.movie.dto.integration.movies.TMDBSearchMovieRequest;
import hu.bozgab.movie.dto.integration.movies.TMDBSearchMovieResponse;

import java.util.List;


public interface TMDBService {

    TMDBDetailsDTO getConfiguration();

    List<TMDBGenreDTO> getGenres();

    TMDBSearchMovieResponse searchMovie(TMDBSearchMovieRequest request);

}
