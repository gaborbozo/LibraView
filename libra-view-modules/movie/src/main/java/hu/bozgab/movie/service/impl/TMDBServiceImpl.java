package hu.bozgab.movie.service.impl;

import hu.bozgab.movie.client.TMDBJsonPlaceholderService;
import hu.bozgab.movie.dto.integration.TMDBGenreDTO;
import hu.bozgab.movie.dto.integration.TMDBSearchMovieRequest;
import hu.bozgab.movie.service.TMDBService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TMDBServiceImpl implements TMDBService {

    private final TMDBJsonPlaceholderService tmdbJsonPlaceholderService;


    @Override
    public List<TMDBGenreDTO> findGenres() {
        return tmdbJsonPlaceholderService.findGenres().getGenres();
    }

    @Override
    public String searchMovie(TMDBSearchMovieRequest request) {
        return tmdbJsonPlaceholderService.searchMovies(
                request.getQuery(), request.getIncludeAdult(), request.getLanguage(), request.getPage()
        );
    }

}
