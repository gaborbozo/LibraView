package hu.bozgab.libraviewtmdb.controller;

import hu.bozgab.LibraViewTMDB.generated.api.GenreApi;
import hu.bozgab.LibraViewTMDB.generated.model.GenreDTO;
import hu.bozgab.libraviewtmdb.service.TMDBDataProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping("/tmdb/data-provider/")
public class GenreController implements GenreApi {

    private final TMDBDataProviderService tmdbDataProviderService;

    @Override
    public Flux<GenreDTO> getGenrePage(ServerWebExchange exchange) {
        return GenreApi.super.getGenrePage(exchange);
    }

    @GetMapping("/syncGenres")
    public Mono<Void> syncGenres() {
        return tmdbDataProviderService.syncGenres();
    }

}
