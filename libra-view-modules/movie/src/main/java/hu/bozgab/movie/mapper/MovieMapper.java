package hu.bozgab.movie.mapper;

import hu.bozgab.movie.domain.Movie;
import hu.bozgab.movie.dto.MovieDTO;

import java.util.List;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    public abstract Movie fromMovieDTOToMovie(MovieDTO movieDTO);

    public abstract List<Movie> fromMovieDTOToMovie(List<MovieDTO> movieDTOList);


    public abstract MovieDTO fromMovieToMovieDTO(Movie movie);

    public abstract List<MovieDTO> fromMovieToMovieDTO(List<Movie> movieList);

}
