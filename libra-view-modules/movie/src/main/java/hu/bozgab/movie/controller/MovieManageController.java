package hu.bozgab.movie.controller;

import hu.bozgab.movie.dto.GenreDTO;
import hu.bozgab.movie.dto.MovieDTO;
import hu.bozgab.movie.service.MovieManagementService;
import hu.bozgab.movie.service.TMDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/movie")
@RestController
public class MovieManageController {

    private MovieManagementService movieManagementService;

    private TMDBService TMDBService;


    @Autowired
    public MovieManageController(MovieManagementService movieManagementService, TMDBService TMDBService) {
        this.movieManagementService = movieManagementService;
        this.TMDBService = TMDBService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MovieDTO>> findAll() {
        return ResponseEntity.ok(
                movieManagementService.findAll()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<String> search() {
        return ResponseEntity.ok(
                TMDBService.findMovie("Inside out")
        );
    }

    @GetMapping("/availableGenres")
    public ResponseEntity<List<GenreDTO>> availableGenres(@RequestBody List<GenreDTO> genreDTOList) {
        return ResponseEntity.ok(
                movieManagementService.availableGenres()
        );
    }
}
