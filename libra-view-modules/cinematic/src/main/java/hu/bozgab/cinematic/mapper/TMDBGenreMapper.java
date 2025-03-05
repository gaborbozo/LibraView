package hu.bozgab.cinematic.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hu.bozgab.cinematic.domain.Genre;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenre;
import org.mapstruct.Builder;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class TMDBGenreMapper {

    /*
        Entity conversions
     */

    @Mapping(target = "tmdbId", source = "tmdbGenre.id")
    @Mapping(target = "id", ignore = true)
    public abstract Genre toGenreEntityForPersist(@MappingTarget Genre genreEntity, TMDBGenre tmdbGenre);

    public List<Genre> toGenreEntitiesForPersist(@MappingTarget List<Genre> genreEntities, List<TMDBGenre> tmdbGenres) {
        return tmdbGenres.stream()
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
        Non-direct conversions
     */

    @Named("reduceTMDBGenresToIds")
    @IterableMapping(qualifiedByName = "reduceTMDBGenreToId")
    public abstract Set<Long> reduceTMDBGenresToIds(List<TMDBGenre> tmdbGenres);

    @Named("reduceTMDBGenreToId")
    public Long reduceTMDBGenreToId(TMDBGenre tmdbGenre) {
        return tmdbGenre != null ? tmdbGenre.getId() : null;
    }

}
