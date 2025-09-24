package hu.bozgab.libraview.cineregistry.controller;

import hu.bozgab.libraview.cineregistry.generated.api.GenreApi;
import hu.bozgab.libraview.cineregistry.generated.model.GenreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cine-registry/")
public class GenreController implements GenreApi {

    @Override
    public Flux<GenreDTO> getGenrePage(ServerWebExchange exchange) {
        return GenreApi.super.getGenrePage(exchange);
    }

}
