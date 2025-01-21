package hu.bozgab.cinematic.service;

import hu.bozgab.cinematic.dto.integration.configuration.TMDBGetDetailsResponse;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenreDTO;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchMovieRequest;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchMovieResponse;

import java.util.List;


public interface TMDBService {

    TMDBGetDetailsResponse getConfiguration();

    List<TMDBGenreDTO> getGenres();

    TMDBSearchMovieResponse searchMovie(TMDBSearchMovieRequest request);

}
