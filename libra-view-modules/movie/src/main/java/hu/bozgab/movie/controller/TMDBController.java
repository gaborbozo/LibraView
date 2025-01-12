package hu.bozgab.movie.controller;

import hu.bozgab.movie.dto.integration.configuration.TMDBDetailsDTO;
import hu.bozgab.movie.dto.integration.movies.TMDBSearchMovieRequest;
import hu.bozgab.movie.dto.integration.movies.TMDBSearchMovieResponse;
import hu.bozgab.movie.service.TMDBService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/movie_module/tmdb")
@RestController
public class TMDBController {

    private final TMDBService tmdbService;

    @GetMapping("/configuration")
    public ResponseEntity<TMDBDetailsDTO> getConfiguration() {
        return ResponseEntity.ok(tmdbService.getConfiguration());
    }

    @GetMapping("/search")
    public ResponseEntity<TMDBSearchMovieResponse> search(@ModelAttribute TMDBSearchMovieRequest request) {
        return ResponseEntity.ok(tmdbService.searchMovie(request));
    }

}
