package hu.bozgab.movie.service.impl;

import hu.bozgab.movie.client.TMDBJsonPlaceholderService;
import hu.bozgab.movie.dto.integration.TMDBGenreDTO;
import hu.bozgab.movie.dto.integration.TMDBSearchMovieRequest;
import hu.bozgab.movie.service.TMDBService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TMDBServiceImpl implements TMDBService {

    private final TMDBJsonPlaceholderService tmdbJsonPlaceholderService;


    @Override
    public List<TMDBGenreDTO> getGenres() {
        // Parsing into a map to discard duplicates
        return new ArrayList<>(Stream.concat(
                        tmdbJsonPlaceholderService.findMovieGenres().getGenres().stream(),
                        tmdbJsonPlaceholderService.findSeriesGenres().getGenres().stream())
                .collect(
                        Collectors.toMap(TMDBGenreDTO::getId, Function.identity(), (_, replacement) -> replacement
                        ))
                .values());
    }

    @Override
    public String searchMovie(TMDBSearchMovieRequest request) {
        return tmdbJsonPlaceholderService.searchMovies(
                request.getQuery(), request.getIncludeAdult(), request.getLanguage(), request.getPage()
        );
    }

}
