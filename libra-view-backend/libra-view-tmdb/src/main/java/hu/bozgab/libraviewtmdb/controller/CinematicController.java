package hu.bozgab.libraviewtmdb.controller;

import hu.bozgab.LibraViewTMDB.generated.api.CinematicApi;
import hu.bozgab.LibraViewTMDB.generated.model.CinematicDTO;
import hu.bozgab.LibraViewTMDB.generated.model.CinematicType;
import hu.bozgab.libraviewtmdb.service.CinematicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping("/tmdb/")
public class CinematicController implements CinematicApi {

    private final CinematicService cinematicService;

    @Override
    public Mono<CinematicDTO> getCinematic(Long tmdbId, CinematicType type, ServerWebExchange exchange) {
        return cinematicService.getCinematic(tmdbId, type);
    }

    @Override
    public Mono<Void> storeCinematic(Long tmdbId, CinematicType type, ServerWebExchange exchange) {
        return cinematicService.storeCinematic(tmdbId, type);
    }

}
