package hu.bozgab.libraview.cineregistry.controller;

import hu.bozgab.libraview.cineregistry.configuration.security.LibraSecurityContext;
import hu.bozgab.libraview.cineregistry.generated.api.CinematicApi;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicDTO;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import hu.bozgab.libraview.cineregistry.generated.model.GeneralCinematicDTO;
import hu.bozgab.libraview.cineregistry.generated.model.PageableRequest;
import hu.bozgab.libraview.cineregistry.service.CinematicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cine-registry/")
public class CinematicController implements CinematicApi {

    private final CinematicService cinematicService;

    @Override
    public Flux<GeneralCinematicDTO> getCinematicPage(CinematicType type, Mono<PageableRequest> pageableRequest, Boolean userLibrary, ServerWebExchange exchange) {
        return LibraSecurityContext.getCurrentUser().flatMapMany(user ->
                pageableRequest.flatMapMany(request -> cinematicService.getCinematicPage(request, type, Boolean.TRUE.equals(userLibrary) ? user.getId() : null)
                ));
    }

    @Override
    public Mono<CinematicDTO> getCinematic(Long referenceId, CinematicType type, ServerWebExchange exchange) {
        return cinematicService.getCinematic(referenceId, type);
    }

    @Override
    public Mono<Void> storeCinematic(Long referenceId, CinematicType type, ServerWebExchange exchange) {
        return cinematicService.storeCinematic(referenceId, type);
    }

}
