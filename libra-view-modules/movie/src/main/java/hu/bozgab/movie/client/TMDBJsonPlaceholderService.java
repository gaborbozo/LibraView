package hu.bozgab.movie.client;

import hu.bozgab.movie.dto.integration.configuration.TMDBDetailsDTO;
import hu.bozgab.movie.dto.integration.genres.TMDBFindGenresResponse;
import hu.bozgab.movie.dto.integration.movies.TMDBSearchMovieResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;


public interface TMDBJsonPlaceholderService {

    /*
        Configuration
     */

    @GetExchange("/configuration")
    TMDBDetailsDTO getConfiguration();

    /*
        Genres
     */

    @GetExchange("/genre/movie/list?language=en")
    TMDBFindGenresResponse findMovieGenres();

    @GetExchange("/genre/tv/list?language=en")
    TMDBFindGenresResponse findSeriesGenres();

    /*
        Movies
     */

    @GetExchange("/search/movie?query={query}&include_adult={includeAdult}&language={language}&page={page}")
    TMDBSearchMovieResponse searchMovies(@PathVariable String query,
                                         @PathVariable boolean includeAdult,
                                         @PathVariable String language,
                                         @PathVariable int page);

}