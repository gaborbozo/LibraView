package hu.bozgab.libraview.cineregistry.controller;

import java.util.List;

import hu.bozgab.libraview.cineregistry.configuration.security.LibraSecurityContext;
import hu.bozgab.libraview.cineregistry.generated.api.UserApi;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import hu.bozgab.libraview.cineregistry.service.UserCinematicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cine-registry/")
public class UserController implements UserApi {

    private final UserCinematicService userCinematicService;


    @Override
    public Flux<Long> checkCinematicInUserLibrary(List<Long> referenceIds, CinematicType type, ServerWebExchange exchange) {
        return LibraSecurityContext.getCurrentUser().flatMapMany(user ->
                userCinematicService.checkCinematicInUserLibrary(user, referenceIds, type)
        );
    }

    @Override
    public Mono<Void> addCinematicToUserLibrary(List<Long> referenceIds, CinematicType type, ServerWebExchange exchange) {
        return LibraSecurityContext.getCurrentUser().flatMap(user ->
                userCinematicService.addCinematicToUserLibrary(user, referenceIds, type)
        );
    }

    @Override
    public Mono<Void> deleteCinematicFromUserLibrary(List<Long> referenceIds, CinematicType type, ServerWebExchange exchange) {
        return LibraSecurityContext.getCurrentUser().flatMap(user ->
                userCinematicService.deleteCinematicFromUserLibrary(user, referenceIds, type)
        );
    }

}
