package hu.bozgab.movie.controller;

import hu.bozgab.movie.dto.integration.TMDBSearchMovieRequest;
import hu.bozgab.movie.service.TMDBService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/tmdb")
@RestController
public class TMDBController {

    private final TMDBService tmdbService;

    
    @GetMapping("/search")
    public ResponseEntity<String> search(@ModelAttribute TMDBSearchMovieRequest request) {
        return ResponseEntity.ok(tmdbService.searchMovie(request));
    }

}
