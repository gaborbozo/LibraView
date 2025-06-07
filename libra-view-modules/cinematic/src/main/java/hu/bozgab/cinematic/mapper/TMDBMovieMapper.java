package hu.bozgab.cinematic.mapper;

import java.util.List;
import java.util.stream.Collectors;

import hu.bozgab.cinematic.dto.MovieDTO;
import hu.bozgab.cinematic.dto.integration.movies.TMDBMovieDetails;
import hu.bozgab.cinematic.dto.integration.movies.TMDBMovieGeneral;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = { TMDBGenreMapper.class }, builder = @Builder(disableBuilder = true))
public abstract class TMDBMovieMapper {

    /*
        DTO conversions
     */

    public List<MovieDTO> toMovieDTOS(List<TMDBMovieGeneral> tmdbMovieGenerals) {
        return tmdbMovieGenerals.stream().map(this::toMovieDTO).collect(Collectors.toList());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tmdbId", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "releaseDate", source = "release_date")
    @Mapping(target = "overview", source = "overview")
    @Mapping(target = "posterPath", source = "poster_path")
    @Mapping(target = "backdropPath", source = "backdrop_path")
    @Mapping(target = "voteAverage", source = "vote_average")
    @Mapping(target = "voteCount", source = "vote_count")
    @Mapping(target = "genreIds", source = "genre_ids")
    @Mapping(target = "popularity", source = "popularity")
    public abstract MovieDTO toMovieDTO(TMDBMovieGeneral tmdbMovieGeneral);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tmdbId", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "releaseDate", source = "release_date")
    @Mapping(target = "overview", source = "overview")
    @Mapping(target = "posterPath", source = "poster_path")
    @Mapping(target = "backdropPath", source = "backdrop_path")
    @Mapping(target = "voteAverage", source = "vote_average")
    @Mapping(target = "voteCount", source = "vote_count")
    @Mapping(target = "genreIds", source = "genres", qualifiedByName = "reduceTMDBGenresToIds")
    @Mapping(target = "popularity", source = "popularity")
    public abstract MovieDTO toMovieDTO(TMDBMovieDetails tmdbMovieDetails);

}
