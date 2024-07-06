package hu.bozgab.movie.service.impl;

import hu.bozgab.movie.domain.Movie;
import hu.bozgab.movie.repository.MovieRepository;
import hu.bozgab.movie.service.MovieManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieManagementServiceImpl implements MovieManagementService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
