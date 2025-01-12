package hu.bozgab.movie.service.impl;

import hu.bozgab.movie.domain.Genre;
import hu.bozgab.movie.dto.GenreDTO;
import hu.bozgab.movie.dto.integration.genres.TMDBGenreDTO;
import hu.bozgab.movie.mapper.GenreMapper;
import hu.bozgab.movie.mapper.TMDBGenreMapper;
import hu.bozgab.movie.repository.GenreRepository;
import hu.bozgab.movie.service.CinematicService;
import hu.bozgab.movie.service.TMDBService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CinematicServiceImpl implements CinematicService {

    private final TMDBService tmdbService;

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;
    private final TMDBGenreMapper tmdbGenreMapper;

    @Override
    public List<GenreDTO> updateGenres() {
        List<TMDBGenreDTO> tmdbGenreDTOS = tmdbService.getGenres();
        List<Genre> genreEntities = genreRepository.findAll();

        tmdbGenreMapper.toGenreEntitiesForPersist(genreEntities, tmdbGenreDTOS);
        genreEntities = genreRepository.saveAll(genreEntities);

        return genreMapper.toGenreDTOS(genreEntities);
    }

    @Override
    public List<GenreDTO> availableGenres() {
        return genreMapper.toGenreDTOS(genreRepository.findAll());
    }

}
