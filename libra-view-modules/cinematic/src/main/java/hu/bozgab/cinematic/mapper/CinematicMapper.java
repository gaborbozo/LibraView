package hu.bozgab.cinematic.mapper;

import hu.bozgab.cinematic.domain.Cinematic;
import hu.bozgab.cinematic.domain.Genre;
import hu.bozgab.cinematic.domain.Movie;
import hu.bozgab.cinematic.domain.Series;
import hu.bozgab.cinematic.dto.CinematicDTO;
import hu.bozgab.cinematic.dto.MovieDTO;
import hu.bozgab.cinematic.dto.SeriesDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public abstract class CinematicMapper {

    /*
        Entity conversions
     */

    public Cinematic toCinematicEntityForPersist(@MappingTarget Cinematic cinematicEntity, CinematicDTO cinematicDTO) {
        if(cinematicDTO instanceof MovieDTO m) {
            if(cinematicEntity == null) {
                cinematicEntity = new Movie();
            }
            return toMovieEntityForPersist((Movie) cinematicEntity, m);
        } else if(cinematicDTO instanceof SeriesDTO s) {
            if(cinematicEntity == null) {
                cinematicEntity = new Series();
            }
            return toSeriesEntityForPersist((Series) cinematicEntity, s);
        } else {
            throw new RuntimeException("Unsupported cinematic type for id " + cinematicDTO.getId());
        }
    }

    @Mapping(target = "id", ignore = true)
    protected abstract Movie toMovieEntityForPersist(@MappingTarget Movie movieEntity, CinematicDTO cinematicDTO);

    @Mapping(target = "id", ignore = true)
    protected abstract Series toSeriesEntityForPersist(@MappingTarget Series seriesEntity, CinematicDTO cinematicDTO);

    public List<Cinematic> toCinematicEntitiesForPersist(@MappingTarget List<Cinematic> cinematicEntities, List<CinematicDTO> cinematicDTOS) {
        return cinematicDTOS.stream()
                .map(dto -> toCinematicEntityForPersist(cinematicEntities.stream()
                        .filter(entity -> dto.getId().equals(entity.getId()))
                        .findFirst()
                        .orElseGet(() -> {
                            Cinematic cinematic = new Cinematic();
                            cinematicEntities.add(cinematic);
                            return cinematic;
                        }), dto)
                )
                .collect(Collectors.toList());
    }

    /*
        DTO conversions
     */

    public CinematicDTO toCinematicDTO(Cinematic cinematicEntity) {
        if(cinematicEntity instanceof Movie m) {
            return toMovieDTO(m, m.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));
        } else if(cinematicEntity instanceof Series s) {
            return toSeriesDTO(s, s.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));
        } else {
            throw new RuntimeException("Unsupported cinematic type for id " + cinematicEntity.getId());
        }
    }

    @Mapping(target = "genreIds", expression = ("java(genreIds)"))
    protected abstract MovieDTO toMovieDTO(Movie movieEntity, @Context Set<Long> genreIds);

    @Mapping(target = "genreIds", expression = ("java(genreIds)"))
    protected abstract SeriesDTO toSeriesDTO(Series seriesEntity, @Context Set<Long> genreIds);

    public List<CinematicDTO> toCinematicDTOS(List<Cinematic> cinematicEntities) {
        return cinematicEntities.stream().map(this::toCinematicDTO).collect(Collectors.toList());
    }

}
