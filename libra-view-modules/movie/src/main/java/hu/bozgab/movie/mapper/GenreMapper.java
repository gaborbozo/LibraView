package hu.bozgab.movie.mapper;

import hu.bozgab.movie.domain.Genre;
import hu.bozgab.movie.dto.GenreDTO;
import hu.bozgab.movie.dto.integration.TMDBGenreDTO;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public abstract class GenreMapper {

    @Mapping(target = "TMDBId", source = "tMDBGenreDTO.id")
    @Mapping(target = "id", ignore = true)
    public abstract Genre fromTMDBGenreToGenre(TMDBGenreDTO tMDBGenreDTO);

    public abstract List<Genre> fromTMDBGenreToGenre(List<TMDBGenreDTO> tMDBGenreDTOList);

    @Mapping(target = "TMDBId", source = "tMDBGenreDTO.id")
    @Mapping(target = "id", ignore = true)
    public abstract GenreDTO fromTMDBGenreToGenreDTO(TMDBGenreDTO tMDBGenreDTO);

    public abstract List<GenreDTO> fromTMDBGenreToGenreDTO(List<TMDBGenreDTO> tMDBGenreDTOList);


    public abstract Genre fromGenreDTOToGenre(GenreDTO genreDTO);

    public abstract List<Genre> fromGenreDTOToGenre(List<GenreDTO> genreDTOList);


    public abstract GenreDTO fromGenreToGenreDTO(Genre genre);

    public abstract List<GenreDTO> fromGenreToGenreDTO(List<Genre> genreList);

}
