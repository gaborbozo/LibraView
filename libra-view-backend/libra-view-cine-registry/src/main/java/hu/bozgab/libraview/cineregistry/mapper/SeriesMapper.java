package hu.bozgab.libraview.cineregistry.mapper;

import java.util.List;

import hu.bozgab.libraview.cineregistry.domain.CinematicGenre;
import hu.bozgab.libraview.cineregistry.domain.Series;
import hu.bozgab.libraview.cineregistry.generated.model.SeriesDTO;
import hu.bozgab.libraview.cineregistry.generated.model.TvSeriesDetails200Response;
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
    public abstract SeriesDTO toSeriesDto(Series seriesEntity, @Context List<CinematicGenre> cinematicGenreEntities);

    @AfterMapping
    protected void afterToSeriesDto(@MappingTarget SeriesDTO seriesDto, @Context List<CinematicGenre> cinematicGenreEntities) {
        seriesDto.setGenreIds(cinematicGenreEntities.stream().map(CinematicGenre::getGenreId).toList());
    }

}