package hu.bozgab.libraview.cineregistry.mapper;

import hu.bozgab.libraview.cineregistry.domain.Series;
import hu.bozgab.libraview.cineregistry.generated.model.TvSeriesDetails200Response;
import hu.bozgab.libraview.common.util.CineRegistryDateFormatter;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), imports = { CineRegistryDateFormatter.class })
public abstract class SeriesMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "referenceId", source = "id")
    @Mapping(target = "title", source = "name")
    @Mapping(target = "releaseDate", expression = "java(CineRegistryDateFormatter.formatIsoLocalDate(series.getFirstAirDate()))")
    @Mapping(target = "overview", source = "overview")
    @Mapping(target = "posterPath", source = "posterPath")
    @Mapping(target = "backdropPath", source = "backdropPath")
    @Mapping(target = "voteAverage", source = "voteAverage")
    @Mapping(target = "voteCount", source = "voteCount")
    @Mapping(target = "popularity", source = "popularity")
    public abstract Series toSeriesEntity(TvSeriesDetails200Response series);

}