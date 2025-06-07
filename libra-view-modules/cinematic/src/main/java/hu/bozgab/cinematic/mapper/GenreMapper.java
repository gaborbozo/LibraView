package hu.bozgab.cinematic.mapper;

import java.util.List;
import java.util.stream.Collectors;

import hu.bozgab.cinematic.domain.Genre;
import hu.bozgab.cinematic.dto.GenreDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class GenreMapper {

    /*
        Entity conversions
     */

    @Mapping(target = "id", ignore = true)
    public abstract Genre toGenreEntityForPersist(@MappingTarget Genre genreEntity, GenreDTO genreDTO);

    public List<Genre> toGenreEntitiesForPersist(@MappingTarget List<Genre> genreEntities, List<GenreDTO> genreDTOS) {
        return genreDTOS.stream()
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

    /*
        DTO conversions
     */

    public abstract GenreDTO toGenreDTO(Genre genreEntity);

    public List<GenreDTO> toGenreDTOS(List<Genre> genreEntities) {
        return genreEntities.stream().map(this::toGenreDTO).collect(Collectors.toList());
    }

}
