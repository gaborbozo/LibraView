package hu.bozgab.movie.client;

import hu.bozgab.movie.dto.helper.TMDBGenreDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface TMDBJsonPlaceholderService {

    @GetExchange("/genre/movie/list?language=en")
    List<TMDBGenreDTO> findGenres();

    @GetExchange("/search/movie?api_key={apiKey}&query={query}&include_adult={includeAdult}&language={language}&page={page}")
    String findMovie(@PathVariable String apiKey,
                     @PathVariable String query,
                     @PathVariable boolean includeAdult,
                     @PathVariable String language,
                     @PathVariable int page);
}