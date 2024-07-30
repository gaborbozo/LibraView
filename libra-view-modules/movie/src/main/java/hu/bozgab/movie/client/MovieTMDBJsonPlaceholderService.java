package hu.bozgab.movie.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface MovieTMDBJsonPlaceholderService {

    @GetExchange("/search/movie?api_key={apiKey}&query={query}&include_adult={includeAdult}&language={language}&page={page}")
    String findMovie(@PathVariable String apiKey,
                     @PathVariable String query,
                     @PathVariable boolean includeAdult,
                     @PathVariable String language,
                     @PathVariable int page);
}