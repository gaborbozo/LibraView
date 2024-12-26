package hu.bozgab.movie.mapper;

import hu.bozgab.movie.domain.Genre;
import hu.bozgab.movie.dto.integration.TMDBGenreDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public abstract class TMDBGenreMapper {

    @Mapping(target = "TMDBId", source = "tmdbGenreDTO.id")
    @Mapping(target = "id", ignore = true)
    public abstract Genre toGenreEntityForPersist(@MappingTarget Genre genreEntity, TMDBGenreDTO tmdbGenreDTO);

    public List<Genre> toGenreEntitiesForPersist(@MappingTarget List<Genre> genreEntities, List<TMDBGenreDTO> tmdbGenreDTOS) {
        return tmdbGenreDTOS.stream()
                .map(dto -> toGenreEntityForPersist(genreEntities.stream()
                        .filter(entity -> dto.getId().equals(entity.getId()))
                        .findFirst()
                        .orElseGet(() -> {
                            Genre genre = new Genre();
                            genreEntities.add(genre);
                            return genre;
                        }), dto)
                )
                .collect(Collectors.toList());
    }

}
