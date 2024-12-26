package hu.bozgab.movie.mapper;

import hu.bozgab.movie.domain.Movie;
import hu.bozgab.movie.dto.MovieDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Movie toMovieEntityForPersist(@MappingTarget Movie movieEntity, MovieDTO movieDTO);

    public List<Movie> toMovieEntitiesForPersist(@MappingTarget List<Movie> movieEntities, List<MovieDTO> movieDTOS) {
        return movieDTOS.stream()
                .map(dto -> toMovieEntityForPersist(movieEntities.stream()
                        .filter(entity -> dto.getId().equals(entity.getId()))
                        .findFirst()
                        .orElseGet(() -> {
                            Movie movie = new Movie();
                            movieEntities.add(movie);
                            return movie;
                        }), dto)
                )
                .collect(Collectors.toList());
    }


    public abstract MovieDTO toMovieDTO(Movie movieEntity);

    public List<MovieDTO> toMovieDTOS(List<Movie> movieEntities) {
        return movieEntities.stream().map(this::toMovieDTO).collect(Collectors.toList());
    }

}
