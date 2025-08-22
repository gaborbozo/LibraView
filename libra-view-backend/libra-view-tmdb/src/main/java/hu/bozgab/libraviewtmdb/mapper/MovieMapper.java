package hu.bozgab.libraviewtmdb.mapper;

import hu.bozgab.LibraViewTMDB.generated.model.MovieDTO;
import hu.bozgab.libraviewtmdb.domain.Movie;
import hu.bozgab.tmdb.generated.model.MovieDetails200Response;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import util.TMDBDateFormatter;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), imports = { TMDBDateFormatter.class })
public abstract class MovieMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "tmdbId", source = "id"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "releaseDate", expression = "java(TMDBDateFormatter.formatIsoLocalDate(movieTmdb.getReleaseDate()))"),
            @Mapping(target = "overview", source = "overview"),
            @Mapping(target = "posterPath", source = "posterPath"),
            @Mapping(target = "backdropPath", source = "backdropPath"),
            @Mapping(target = "voteAverage", source = "voteAverage"),
            @Mapping(target = "voteCount", source = "voteCount"),
            @Mapping(target = "popularity", source = "popularity"),
    })
    public abstract Movie toMovieEntity(MovieDetails200Response movieTmdb);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "tmdbId"),
            @Mapping(target = "title"),
            //@Mapping(target = "releaseDate"),
            @Mapping(target = "overview"),
            @Mapping(target = "posterPath"),
            @Mapping(target = "backdropPath"),
            @Mapping(target = "voteAverage"),
            @Mapping(target = "voteCount"),
            @Mapping(target = "popularity"),
    })
    public abstract MovieDTO toMovieDto(Movie movie);

}
