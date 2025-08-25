package hu.bozgab.libraview.cineregistry.controller;

import hu.bozgab.libraview.cineregistry.generated.api.GenreApi;
import hu.bozgab.libraview.cineregistry.generated.model.GenreDTO;
import hu.bozgab.libraview.cineregistry.service.CinematicDataProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cine-registry/data-provider/")
public class GenreController implements GenreApi {

    private final CinematicDataProviderService cinematicDataProviderService;

    @Override
    public Flux<GenreDTO> getGenrePage(ServerWebExchange exchange) {
        return GenreApi.super.getGenrePage(exchange);
    }

    @GetMapping("/syncGenres")
    public Mono<Void> syncGenres() {
        return cinematicDataProviderService.syncGenres();
    }

}
