package hu.bozgab.movie.service.impl;

import hu.bozgab.movie.dto.GenreDTO;
import hu.bozgab.movie.dto.MovieDTO;
import hu.bozgab.movie.mapper.MovieMapper;
import hu.bozgab.movie.repository.GenreRepository;
import hu.bozgab.movie.repository.MovieRepository;
import hu.bozgab.movie.service.MovieManagementService;
import hu.bozgab.movie.service.TMDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieManagementServiceImpl implements MovieManagementService {

    private TMDBService tmdbService;

    private MovieRepository movieRepository;

    private GenreRepository genreRepository;

    private MovieMapper movieMapper;

    //region Injecting beans
    @Autowired
    public void setServices(TMDBService tMDBService) {
        this.tmdbService = tMDBService;
    }

    @Autowired
    public void setRepositories(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }
    //endregion

    @Autowired
    public void setMappers(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDTO> findAll() {
        return movieMapper.movieListToMovieDTOList(movieRepository.findAll());
    }

    @Override
    public List<GenreDTO> availableGenres() {
        return null;
    }


}
