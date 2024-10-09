package hu.bozgab.movie.controller;

import hu.bozgab.movie.dto.GenreDTO;
import hu.bozgab.movie.service.MovieManagementService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/movie")
@RestController
public class MovieManageController {

    private final MovieManagementService movieManagementService;


    @GetMapping("/updateGenres")
    public ResponseEntity<List<GenreDTO>> updateGenres() {
        return ResponseEntity.ok(movieManagementService.updateGenres());
    }

    @GetMapping("/availableGenres")
    public ResponseEntity<List<GenreDTO>> availableGenres() {
        return ResponseEntity.ok(movieManagementService.availableGenres());
    }

}
