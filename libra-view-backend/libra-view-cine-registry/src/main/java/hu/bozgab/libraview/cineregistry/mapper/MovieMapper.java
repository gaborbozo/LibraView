package hu.bozgab.libraview.cineregistry.mapper;

import java.util.List;

import hu.bozgab.libraview.cineregistry.domain.CinematicGenre;
import hu.bozgab.libraview.cineregistry.domain.Movie;
import hu.bozgab.libraview.cineregistry.generated.model.MovieDTO;
import hu.bozgab.libraview.cineregistry.generated.model.MovieDetails200Response;
import hu.bozgab.libraview.common.util.CineRegistryDateFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;


@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        uses = { GenreMapper.class },
        imports = { CineRegistryDateFormatter.class }
)
public abstract class MovieMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "referenceId", source = "id"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "releaseDate", expression = "java(CineRegistryDateFormatter.formatIsoLocalDate(movie.getReleaseDate()))"),
            @Mapping(target = "overview", source = "overview"),
            @Mapping(target = "posterPath", source = "posterPath"),
            @Mapping(target = "backdropPath", source = "backdropPath"),
            @Mapping(target = "voteAverage", source = "voteAverage"),
            @Mapping(target = "voteCount", source = "voteCount"),
            @Mapping(target = "popularity", source = "popularity"),
    })
    public abstract Movie toMovieEntity(MovieDetails200Response movie);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "id"),
            @Mapping(target = "referenceId"),
            @Mapping(target = "title"),
            //@Mapping(target = "releaseDate"),
            @Mapping(target = "overview"),
            @Mapping(target = "posterPath"),
            @Mapping(target = "backdropPath"),
            @Mapping(target = "voteAverage"),
            @Mapping(target = "voteCount"),
            @Mapping(target = "popularity"),
    })
    public abstract MovieDTO toMovieDto(Movie movieEntity, @Context List<CinematicGenre> cinematicGenreEntities);

    @AfterMapping
    protected void afterToMovieDto(@MappingTarget MovieDTO movieDto, @Context List<CinematicGenre> cinematicGenreEntities) {
        movieDto.setGenreIds(cinematicGenreEntities.stream().map(CinematicGenre::getGenreId).toList());
    }

}
