package hu.bozgab.movie.client;

import hu.bozgab.movie.dto.integration.TMDBFindGenresResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;


public interface TMDBJsonPlaceholderService {

    @GetExchange("/genre/movie/list?language=en")
    TMDBFindGenresResponse findMovieGenres();

    @GetExchange("/genre/tv/list?language=en")
    TMDBFindGenresResponse findSeriesGenres();

    @GetExchange("/search/movie?query={query}&include_adult={includeAdult}&language={language}&page={page}")
    String searchMovies(@PathVariable String query,
                        @PathVariable boolean includeAdult,
                        @PathVariable String language,
                        @PathVariable int page);

}