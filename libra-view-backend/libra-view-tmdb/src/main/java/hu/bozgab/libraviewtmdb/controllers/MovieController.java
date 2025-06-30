package hu.bozgab.libraviewtmdb.controllers;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import hu.bozgab.libraviewtmdb.generated.api.MovieApi;
import hu.bozgab.libraviewtmdb.generated.model.CinematicDTO;
import hu.bozgab.libraviewtmdb.tmdb.TMDBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/tmdb/")
public class MovieController implements MovieApi {

    @Autowired
    TMDBClient tmdbClient;

    @Override
    public Flux<CinematicDTO> getMovieDetails(List<Long> tmdbIds, ServerWebExchange exchange) {
        var resp = tmdbClient.searchMovie("A quiet", true, "en", null, null, null, null);

        assert resp.getResults() != null;
        return Flux.fromStream(Objects.requireNonNull(resp.getResults().stream().map(v -> {
            CinematicDTO dto = new CinematicDTO();

            dto.setTitle(v.getTitle());

            return dto;
        }))).delayElements(Duration.ofMillis(200));
    }

}
