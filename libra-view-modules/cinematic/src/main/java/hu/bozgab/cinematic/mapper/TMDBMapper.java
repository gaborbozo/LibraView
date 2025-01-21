package hu.bozgab.cinematic.mapper;

import hu.bozgab.cinematic.domain.Genre;
import hu.bozgab.cinematic.dto.MovieDTO;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenreDTO;
import hu.bozgab.cinematic.dto.integration.movies.TMDBMovieDetailsDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public abstract class TMDBMapper {

    /*
        Entity conversions
     */

    @Mapping(target = "tmdbId", source = "tmdbGenreDTO.id")
    @Mapping(target = "id", ignore = true)
    public abstract Genre toGenreEntityForPersist(@MappingTarget Genre genreEntity, TMDBGenreDTO tmdbGenreDTO);

    public List<Genre> toGenreEntitiesForPersist(@MappingTarget List<Genre> genreEntities, List<TMDBGenreDTO> tmdbGenreDTOS) {
        return tmdbGenreDTOS.stream()
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

    public List<MovieDTO> toMovieDTOS(List<TMDBMovieDetailsDTO> tmdbMovieDetailsDTOS) {
        return tmdbMovieDetailsDTOS.stream().map(this::toMovieDTO).collect(Collectors.toList());
    }

    @Mapping(target = "tmdbId", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "releaseDate", source = "release_date")
    @Mapping(target = "overview", source = "overview")
    @Mapping(target = "posterPath", source = "poster_path")
    @Mapping(target = "backdropPath", source = "backdrop_path")
    @Mapping(target = "voteAverage", source = "vote_average")
    @Mapping(target = "voteCount", source = "vote_count")
    @Mapping(target = "genreIds", source = "genre_ids")
    public abstract MovieDTO toMovieDTO(TMDBMovieDetailsDTO tmdbMovieDetailsDTO);

}
