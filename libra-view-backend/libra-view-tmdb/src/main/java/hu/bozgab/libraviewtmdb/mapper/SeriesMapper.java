package hu.bozgab.libraviewtmdb.mapper;

import hu.bozgab.libraviewtmdb.domain.Series;
import hu.bozgab.tmdb.generated.model.TvSeriesDetails200Response;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import util.TMDBDateFormatter;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), imports = { TMDBDateFormatter.class })
public abstract class SeriesMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "tmdbId", source = "id")
    @Mapping(target = "title", source = "name")
    @Mapping(target = "releaseDate", expression = "java(TMDBDateFormatter.formatIsoLocalDate(seriesTmdb.getFirstAirDate()))")
    @Mapping(target = "overview", source = "overview")
    @Mapping(target = "posterPath", source = "posterPath")
    @Mapping(target = "backdropPath", source = "backdropPath")
    @Mapping(target = "voteAverage", source = "voteAverage")
    @Mapping(target = "voteCount", source = "voteCount")
    @Mapping(target = "popularity", source = "popularity")
    public abstract Series toSeriesEntity(TvSeriesDetails200Response seriesTmdb);

}