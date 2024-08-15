package hu.bozgab.movie.mapper;

import hu.bozgab.movie.domain.Genre;
import hu.bozgab.movie.dto.GenreDTO;
import hu.bozgab.movie.dto.helper.TMDBGenreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "TMDBId", source = "tMDBGenreDTO.id")
    @Mapping(target = "id", ignore = true)
    GenreDTO tMDBGenreDTOtoGenreDTO(TMDBGenreDTO tMDBGenreDTO);

    List<GenreDTO> tMDBGenreDTOListtoGenreDTOList(List<TMDBGenreDTO> tMDBGenreDTOList);

    Genre genreDTOtoGenre(GenreDTO genreDTO);

    GenreDTO genretoGenreDTO(Genre genre);

    List<Genre> genreDTOListToGenreList(List<GenreDTO> genreDTOList);

    List<GenreDTO> genreListToGenreDTOList(List<Genre> genreList);
}
