package hu.bozgab.libraview.cineregistry.controller;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import hu.bozgab.libraview.cineregistry.generated.api.MovieApi;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicDTO;
import hu.bozgab.libraview.cineregistry.tmdb.TMDBClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cine-registry/")
public class MovieController implements MovieApi {

    private final TMDBClient tmdbClient;

    @Override
    public Flux<CinematicDTO> getMovieDetails(List<Long> referenceIds, ServerWebExchange exchange) {
        var resp = tmdbClient.searchMovie("A quiet", true, "en", null, null, null, null);

        assert resp.getResults() != null;
        return Flux.fromStream(Objects.requireNonNull(resp.getResults().stream().map(v -> {
            CinematicDTO dto = new CinematicDTO();

            dto.setTitle(v.getTitle());

            return dto;
        }))).delayElements(Duration.ofMillis(200));
    }

}
