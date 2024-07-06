package hu.bozgab.movie.controller;

import hu.bozgab.movie.domain.Movie;
import hu.bozgab.movie.service.MovieManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/movie")
@RestController
public class MovieManageController {

    private MovieManagementService movieManagementService;

    @Autowired
    public MovieManageController(MovieManagementService movieManagementService) {
        this.movieManagementService = movieManagementService;
    }

    @GetMapping("/findAll")
    public List<Movie> findAll() {
        return movieManagementService.findAll();
    }

}
