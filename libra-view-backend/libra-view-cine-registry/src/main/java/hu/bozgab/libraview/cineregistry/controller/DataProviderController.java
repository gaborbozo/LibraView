package hu.bozgab.libraview.cineregistry.controller;

import hu.bozgab.libraview.cineregistry.service.CinematicDataProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cine-registry/data-provider/")
public class DataProviderController {

    private final CinematicDataProviderService cinematicDataProviderService;

    @GetMapping("/syncGenres")
    public Mono<Void> syncGenres() {
        return cinematicDataProviderService.syncGenres();
    }

}
