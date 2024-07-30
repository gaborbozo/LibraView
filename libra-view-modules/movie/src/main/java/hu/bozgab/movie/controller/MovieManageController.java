package hu.bozgab.movie.controller;

import hu.bozgab.movie.dto.GenreDTO;
import hu.bozgab.movie.dto.MovieDTO;
import hu.bozgab.movie.service.MovieManagementService;
import hu.bozgab.movie.service.MovieTMDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/movie")
@RestController
public class MovieManageController {

    private MovieManagementService movieManagementService;

    private MovieTMDBService movieTMDBService;


    @Autowired
    public MovieManageController(MovieManagementService movieManagementService, MovieTMDBService movieTMDBService) {
        this.movieManagementService = movieManagementService;
        this.movieTMDBService = movieTMDBService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MovieDTO>> findAll() {
        return ResponseEntity.ok(movieManagementService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<String> search() {
        return movieTMDBService.findMovie("Inside out");
    }

    @PostMapping("/updateGenre")
    public ResponseEntity<GenreDTO> updateGenres(@RequestBody List<GenreDTO> genreDTOList) {
        return ResponseEntity.ok(genreDTOList.get(0));
    }
}
