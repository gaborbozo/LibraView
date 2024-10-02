package hu.bozgab.movie.mapper;

import hu.bozgab.movie.domain.Movie;
import hu.bozgab.movie.dto.MovieDTO;

import java.util.List;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie movieDTOToMovie(MovieDTO movieDTO);

    MovieDTO movieToMovieDTO(Movie movie);

    List<Movie> movieListToMovieList(List<MovieDTO> movieDTOList);

    List<MovieDTO> movieListToMovieDTOList(List<Movie> movieList);

}
