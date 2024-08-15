package hu.bozgab.movie.service.impl;

import hu.bozgab.movie.client.TMDBJsonPlaceholderService;
import hu.bozgab.movie.domain.Genre;
import hu.bozgab.movie.dto.GenreDTO;
import hu.bozgab.movie.mapper.GenreMapper;
import hu.bozgab.movie.repository.GenreRepository;
import hu.bozgab.movie.service.TMDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TMDBServiceImpl implements TMDBService {

    private GenreRepository genreRepository;

    private GenreMapper genreMapper;

    private TMDBJsonPlaceholderService tmdbJsonPlaceholderService;

    //region Injecting beans
    @Autowired
    public void setRepositories(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setMappers(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Autowired
    public void setPlaceholderServices(TMDBJsonPlaceholderService TMDBJsonPlaceholderService) {
        this.tmdbJsonPlaceholderService = TMDBJsonPlaceholderService;
    }
    //endregion

    @Override
    public List<GenreDTO> updateGenres() {
        List<Genre> genreList = genreMapper.genreDTOListToGenreList(
                genreMapper.tMDBGenreDTOListtoGenreDTOList(
                        tmdbJsonPlaceholderService.findGenres()
                )
        );

        return genreMapper.genreListToGenreDTOList(
                genreRepository.saveAll(genreList)
        );
    }

    @Override
    public String findMovie(String searchKey) {
        return tmdbJsonPlaceholderService.findMovie(
                "api_key",
                searchKey, false, "en-US", 1
        );
    }
}
