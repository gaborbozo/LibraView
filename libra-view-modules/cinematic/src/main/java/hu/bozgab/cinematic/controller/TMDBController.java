package hu.bozgab.cinematic.controller;

import hu.bozgab.cinematic.dto.integration.configuration.TMDBGetDetailsResponse;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchMovieRequest;
import hu.bozgab.cinematic.dto.integration.search.TMDBSearchResponse;
import hu.bozgab.cinematic.service.TMDBService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/cinematic_module/tmdb")
@RestController
public class TMDBController {

    private final TMDBService tmdbService;


    @GetMapping("/configuration")
    public ResponseEntity<TMDBGetDetailsResponse> getConfiguration() {
        return ResponseEntity.ok(tmdbService.getConfiguration());
    }

    @GetMapping("/search")
    public ResponseEntity<TMDBSearchResponse> search(@ModelAttribute TMDBSearchMovieRequest request) {
        return ResponseEntity.ok(tmdbService.search(request));
    }

}
