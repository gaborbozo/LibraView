package hu.bozgab.movie.service.impl;

import hu.bozgab.movie.domain.Genre;
import hu.bozgab.movie.dto.GenreDTO;
import hu.bozgab.movie.dto.integration.TMDBGenreDTO;
import hu.bozgab.movie.mapper.GenreMapper;
import hu.bozgab.movie.repository.GenreRepository;
import hu.bozgab.movie.service.MovieManagementService;
import hu.bozgab.movie.service.TMDBService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MovieManagementServiceImpl implements MovieManagementService {

    private final TMDBService tmdbService;

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;


    @Override
    public List<GenreDTO> updateGenres() {
        List<TMDBGenreDTO> tMDBGenres = tmdbService.findGenres();
        List<Long> persistedTMDBIds = genreRepository.findAllTMDBIds();

        List<Genre> genreList = tMDBGenres.stream()
                .filter(g -> !persistedTMDBIds.contains(g.getId()))
                .map(genreMapper::fromTMDBGenreToGenre)
                .toList();
        genreRepository.saveAll(genreList);

        return genreMapper.fromGenreToGenreDTO(genreList);
    }

    @Override
    public List<GenreDTO> availableGenres() {
        return genreMapper.fromGenreToGenreDTO(genreRepository.findAll());
    }

}
