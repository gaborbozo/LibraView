package hu.bozgab.libraview.cineregistry.mapper;

import java.util.List;
import java.util.stream.Collectors;

import hu.bozgab.libraview.cineregistry.domain.Genre;
import hu.bozgab.libraview.cineregistry.generated.model.GenreDTO;
import hu.bozgab.libraview.cineregistry.generated.model.GenreMovieList200ResponseGenresInner;
import hu.bozgab.libraview.cineregistry.generated.model.GenreTvList200ResponseGenresInner;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class GenreMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "referenceId", source = "id"),
            @Mapping(target = "name"),
    })
    public abstract Genre toGenreEntity(GenreMovieList200ResponseGenresInner genre);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "referenceId", source = "id"),
            @Mapping(target = "name"),
    })
    public abstract Genre toGenreEntity(GenreTvList200ResponseGenresInner genre);

    public abstract GenreDTO toGenreDTO(Genre genreEntity);

    public List<GenreDTO> toGenreDTOS(List<Genre> genreEntities) {
        return genreEntities.stream().map(this::toGenreDTO).collect(Collectors.toList());
    }

}
