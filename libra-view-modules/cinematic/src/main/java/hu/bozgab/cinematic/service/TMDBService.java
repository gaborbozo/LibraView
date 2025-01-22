package hu.bozgab.cinematic.service;

import hu.bozgab.cinematic.dto.integration.configuration.TMDBGetDetailsResponse;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenreDTO;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchRequest;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchResponse;

import java.util.List;


public interface TMDBService {

    TMDBGetDetailsResponse getConfiguration();

    List<TMDBGenreDTO> getGenres();

    TMDBSearchResponse search(TMDBSearchRequest request);

}
