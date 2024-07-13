package hu.bozgab.movie.service.impl;

import hu.bozgab.movie.dto.MovieDTO;
import hu.bozgab.movie.mapper.MovieMapper;
import hu.bozgab.movie.repository.MovieRepository;
import hu.bozgab.movie.service.MovieManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieManagementServiceImpl implements MovieManagementService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieMapper movieMapper;

    @Override
    public List<MovieDTO> findAll() {
        return movieMapper.movieListToMovieDTOList(movieRepository.findAll());
    }
}
