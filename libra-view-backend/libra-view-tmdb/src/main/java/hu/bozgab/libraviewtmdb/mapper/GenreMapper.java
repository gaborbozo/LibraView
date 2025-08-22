package hu.bozgab.libraviewtmdb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import hu.bozgab.LibraViewTMDB.generated.model.GenreDTO;
import hu.bozgab.libraviewtmdb.domain.Genre;
import hu.bozgab.tmdb.generated.model.GenreMovieList200ResponseGenresInner;
import hu.bozgab.tmdb.generated.model.GenreTvList200ResponseGenresInner;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class GenreMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "tmdbId", source = "id"),
            @Mapping(target = "name"),
    })
    public abstract Genre toGenreEntity(GenreMovieList200ResponseGenresInner genreTmdb);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "tmdbId", source = "id"),
            @Mapping(target = "name"),
    })
    public abstract Genre toGenreEntity(GenreTvList200ResponseGenresInner genreTmdb);

    public abstract GenreDTO toGenreDTO(Genre genreEntity);

    public List<GenreDTO> toGenreDTOS(List<Genre> genreEntities) {
        return genreEntities.stream().map(this::toGenreDTO).collect(Collectors.toList());
    }

}
