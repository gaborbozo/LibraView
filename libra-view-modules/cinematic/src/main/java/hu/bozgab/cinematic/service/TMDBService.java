package hu.bozgab.cinematic.service;

import java.util.List;

import hu.bozgab.cinematic.dto.integration.configuration.TMDBGetDetailsResponse;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenre;
import hu.bozgab.cinematic.dto.integration.movies.TMDBMovieDetails;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchRequest;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchResponse;


public interface TMDBService {

    TMDBGetDetailsResponse getConfiguration();

    List<TMDBGenre> getGenres();

    TMDBSearchResponse search(TMDBSearchRequest request);

    TMDBMovieDetails getMovieDetails(Long movieId);

}
