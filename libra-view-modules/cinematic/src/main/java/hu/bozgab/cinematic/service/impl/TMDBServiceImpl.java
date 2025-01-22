package hu.bozgab.cinematic.service.impl;

import hu.bozgab.cinematic.client.TMDBJsonPlaceholderService;
import hu.bozgab.cinematic.client.subpaths.TMDBGenreSubPath;
import hu.bozgab.cinematic.client.subpaths.TMDBSearchSubPath;
import hu.bozgab.cinematic.dto.integration.configuration.TMDBGetDetailsResponse;
import hu.bozgab.cinematic.dto.integration.core.TMDBGeneralRequest;
import hu.bozgab.cinematic.dto.integration.core.TMDBGeneralResponse;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenreDTO;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchMovieRequest;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchMovieResponse;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchRequest;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchResponse;
import hu.bozgab.cinematic.exception.UnsupportedTypeException;
import hu.bozgab.cinematic.mapper.TMDBMapper;
import hu.bozgab.cinematic.service.CinematicCacheService;
import hu.bozgab.cinematic.service.TMDBService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TMDBServiceImpl implements TMDBService {

    private final TMDBJsonPlaceholderService tmdbJsonPlaceholderService;

    @Setter
    @Lazy
    @Autowired
    private CinematicCacheService cinematicCacheService;

    private final TMDBMapper tmdbMapper;
    private final ObjectMapper objectMapper;


    @Override
    public TMDBGetDetailsResponse getConfiguration() {
        return tmdbJsonPlaceholderService.getDetails();
    }

    @Override
    public List<TMDBGenreDTO> getGenres() {
        // Parsing into a map to discard duplicates
        return new ArrayList<>(Stream.concat(
                        tmdbJsonPlaceholderService.genre(TMDBGenreSubPath.tv).getGenres().stream(),
                        tmdbJsonPlaceholderService.genre(TMDBGenreSubPath.movie).getGenres().stream())
                .collect(
                        Collectors.toMap(TMDBGenreDTO::getId, Function.identity(), (_, replacement) -> replacement
                        ))
                .values());
    }

    @Override
    public TMDBSearchResponse search(TMDBSearchRequest request) {
        if(request instanceof TMDBSearchMovieRequest movieRequest) {
            TMDBSearchMovieResponse response = createResponseObjectFromRawString(
                    tmdbJsonPlaceholderService.search(TMDBSearchSubPath.movie, createParameterMapFromObject(movieRequest)),
                    TMDBSearchMovieResponse.class
            );

            // TODO This caching must be temporary
            //  in future versions frontend needs to handle the search and only on add backend will get the cinematic element by id
            tmdbMapper.toMovieDTOS(response.getResults()).forEach(cinematicCacheService::updateCinematic);

            return response;
        } else {
            throw new UnsupportedTypeException(request.getClass());
        }
    }

    /*
        Helper functions
     */

    private <T extends TMDBGeneralRequest> Map<String, String> createParameterMapFromObject(T request) {
        Map<String, String> params = new HashMap<>();
        Class<?> clazz = request.getClass();

        for(Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(request);
                params.put(field.getName(), value != null ? value.toString() : null);
            } catch(IllegalAccessException e) {
                throw new RuntimeException("Failed to access field: " + field.getName(), e);
            }
        }

        return params;
    }

    private <T extends TMDBGeneralResponse> T createResponseObjectFromRawString(String rawResponse, Class<T> clazz) {
        T response;
        try {
            response = objectMapper.readValue(rawResponse, clazz);
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

}
