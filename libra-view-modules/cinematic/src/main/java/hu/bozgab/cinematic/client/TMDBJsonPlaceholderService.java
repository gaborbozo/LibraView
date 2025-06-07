package hu.bozgab.cinematic.client;

import java.util.Map;

import hu.bozgab.cinematic.client.subpaths.TMDBGenreSubPath;
import hu.bozgab.cinematic.client.subpaths.TMDBSearchSubPath;
import hu.bozgab.cinematic.dto.integration.configuration.TMDBGetDetailsResponse;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGetGenresResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;


public interface TMDBJsonPlaceholderService {

    @GetExchange("/configuration")
    TMDBGetDetailsResponse getDetails();

    @GetExchange("/genre/{subPath}/list?language=en")
    TMDBGetGenresResponse genre(@PathVariable TMDBGenreSubPath subPath);

    @GetExchange("/search/{subPath}")
    String search(@PathVariable TMDBSearchSubPath subPath, @RequestParam Map<String, String> queryParams);

    @GetExchange("/movie/{id}?language=en")
    String movieDetails(@PathVariable Long id);

}